package com.msch.helpapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.msch.helpapp.R
import com.msch.helpapp.adapters.SearchEventsAdapter
import com.msch.helpapp.data.EventDetailsRepo
import com.msch.helpapp.implementations.*
import com.msch.helpapp.interactors.*
import kotlinx.android.synthetic.main.sf_viewpager_events_fragment.view.*
import javax.inject.Inject

class EventsSearchFragment: Fragment() {
    @Inject
    lateinit var interactors: Interactors

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.sf_viewpager_events_fragment, container, false)
        val searchAdapter = SearchEventsAdapter()
        interactors = Interactors(
            AddEvents(EventDetailsRepo(EventDetailsDsImpl())),
            FilterNews(FilterNewsImpl()),
            AddCategories(CategoryItemsDsImpl()),
            GetUserInfo(GetUserInfoImpl()),
            GenerateSearchResults(SearchResultsGenerator()),
            FirebaseLogin(FirebaseLoginImpl()),
            FirebaseReg(FirebaseRegImpl())
        )
        val data = interactors.generateSearchResults()

        view.sf_ve_recycler.layoutManager = LinearLayoutManager(requireActivity())
        view.sf_ve_recycler.adapter = searchAdapter
        searchAdapter.submitList(data)

        val numOfResults = view.sf_ve_results_value as TextView
        val keyword = view.sf_ve_keywords_value as TextView
        keyword.text = data[0].results
        numOfResults.text = data.size.toString()

        return view
    }
}