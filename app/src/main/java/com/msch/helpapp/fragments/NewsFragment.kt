package com.msch.helpapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.reflect.TypeToken
import com.msch.helpapp.R
import com.msch.helpapp.adapters.NewsAdapter
import com.msch.helpapp.concurrency.FileCoroutine.fileWorksThread
import com.msch.helpapp.concurrency.FileCoroutine.logThread
import com.msch.helpapp.models.EventDetails
import kotlinx.android.synthetic.main.fragment_help_screen.view.*
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO

class NewsFragment : Fragment() {
    val listType = object : TypeToken<List<EventDetails>>() {}.type
    val EVENTS_INFORMATION = "events_information"

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        var data: List<EventDetails>
        var filteredData: List<EventDetails> = ArrayList()
        val view = inflater.inflate(R.layout.fragment_news_screen, container, false)
        val newsAdapter = NewsAdapter()

        runBlocking(IO) {
            data = fileWorksThread(requireContext(), listType, EVENTS_INFORMATION).filterIsInstance<EventDetails>()
            filteredData = filterNews(data)
        }

        logThread("UIMain")
        view.recycler_view.layoutManager = LinearLayoutManager(requireActivity())
        view.recycler_view.adapter = newsAdapter
        newsAdapter.submitList(filteredData)

        return view
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