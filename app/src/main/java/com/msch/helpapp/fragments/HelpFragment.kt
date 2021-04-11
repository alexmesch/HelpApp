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
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.msch.helpapp.*
import com.msch.helpapp.adapters.CategoryViewAdapter
import com.msch.helpapp.database.FirebaseOperations.retrieveCategoriesData
import com.msch.helpapp.models.CategoryItems
import kotlinx.android.synthetic.main.fragment_help_screen.view.*
import kotlinx.coroutines.*

class HelpFragment : Fragment() {
    private val lifecycleScope = MainScope()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_help_screen, container, false)
        val categoryAdapter = CategoryViewAdapter()
        var data: List<CategoryItems>
        val loadingScreen = view.findViewById<FrameLayout>(R.id.hf_loadingScreen)

        lifecycleScope.launch {
            val dbRef = Firebase.database.reference
            dbRef.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    data = retrieveCategoriesData(dataSnapshot, "RealmCategories")
                    view.recycler_view.adapter = categoryAdapter
                    view.recycler_view.layoutManager = GridLayoutManager(requireActivity(), 2)
                    categoryAdapter.submitList(data)
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
}