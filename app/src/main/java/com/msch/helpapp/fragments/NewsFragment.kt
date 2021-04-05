package com.msch.helpapp.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.gson.reflect.TypeToken
import com.msch.helpapp.R
import com.msch.helpapp.adapters.NewsAdapter
import com.msch.helpapp.concurrency.FileCoroutine.logThread
import com.msch.helpapp.database.FirebaseOperations
import com.msch.helpapp.database.RealmConfig.defineRealmConfig
import com.msch.helpapp.database.RealmEvents
import com.msch.helpapp.models.EventDetails
import com.msch.helpapp.objects.JsonParser.parseJson
import io.realm.Realm
import kotlinx.android.synthetic.main.fragment_help_screen.view.*
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO

class NewsFragment : Fragment() {
    private val listType = object : TypeToken<List<EventDetails>>() {}.type
    private val EVENTS_INFORMATION = "events_information"
    private val lifecycleScope = MainScope()


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
                    data = FirebaseOperations.retrieveEventsData(dataSnapshot, "RealmEvents")
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
        val filteredNews: List<EventDetails>
        val filter: String = arguments?.getString("categoryID").toString()

        filteredNews = if (filter == "null") {
            newsData
        } else {
            newsData.filter { it.eventCategory == filter }
        }
        return filteredNews
    }
}