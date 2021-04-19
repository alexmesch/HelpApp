package com.msch.helpapp.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.msch.helpapp.R
import com.msch.helpapp.adapters.NewsAdapter
import com.msch.helpapp.database.FirebaseOperations.retrieveFirebaseData
import com.msch.helpapp.models.EventDetails
import kotlinx.android.synthetic.main.fragment_help_screen.view.*
import kotlinx.coroutines.*

class NewsFragment : Fragment() {
    private val lifecycleScope = MainScope()
    private val dataType = EventDetails::class.java.newInstance()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var data: List<EventDetails>
        var filteredData: List<EventDetails>
        val view = inflater.inflate(R.layout.fragment_news_screen, container, false)
        val newsAdapter = NewsAdapter()
        val loadingScreen = view.findViewById<FrameLayout>(R.id.nf_loadingScreen)

        lifecycleScope.launch {
            val dbRef = Firebase.database.reference
            dbRef.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    data = retrieveFirebaseData(dataSnapshot, "RealmEvents", dataType)
                    filteredData = filterNews(data)
                    view.recycler_view.layoutManager = LinearLayoutManager(requireActivity())
                    view.recycler_view.adapter = newsAdapter
                    newsAdapter.submitList(filteredData)
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    Log.w("Firebase", "Load: cancelled", databaseError.toException())
                }
            })
        }
        loadingScreen.visibility = GONE
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        lifecycleScope.cancel()
    }

    private fun filterNews(newsData: List<EventDetails>): List<EventDetails> {
        val filter = arguments?.getString("categoryID")
        return newsData.filter { filter == null || it.eventCategory == filter}
    }
}