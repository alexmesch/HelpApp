package com.msch.helpapp.fragments

import android.os.Bundle
import android.util.EventLog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.msch.helpapp.R
import com.msch.helpapp.adapters.NewsAdapter
import com.msch.helpapp.models.EventDetails
import com.msch.helpapp.objects.JsonParser
import kotlinx.android.synthetic.main.fragment_help_screen.view.*

class NewsFragment: Fragment() {
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_news_screen, container, false)
        val newsAdapter = NewsAdapter()
        val fileContent = JsonParser.openFile(activity?.assets!!.open("events_information"))
        val data = JsonParser.parseEventDetails(fileContent)

        view.recycler_view.layoutManager = LinearLayoutManager(requireActivity())
        view.recycler_view.adapter = newsAdapter
        newsAdapter.submitList(filterNews(data))

        return view
    }

    private fun filterNews(newsData: List<EventDetails>): List<EventDetails> {
        val filteredNews: List<EventDetails>
        val filter: String = arguments?.getString("categoryID").toString()

        filteredNews = if (filter == "null") {
            newsData
        }
        else {
            newsData.filter{it.eventCategory == filter}
        }
        return filteredNews
    }


}