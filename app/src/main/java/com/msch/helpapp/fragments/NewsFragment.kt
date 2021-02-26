package com.msch.helpapp.fragments

import android.os.Bundle
import android.util.EventLog
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.reflect.TypeToken
import com.msch.helpapp.R
import com.msch.helpapp.adapters.NewsAdapter
import com.msch.helpapp.concurrency.FileCoroutine
import com.msch.helpapp.concurrency.FileCoroutine.fileWorksThread
import com.msch.helpapp.models.CategoryItems
import com.msch.helpapp.models.EventDetails
import com.msch.helpapp.models.NewsItem
import com.msch.helpapp.objects.JsonParser
import com.msch.helpapp.objects.JsonParser.parseJson
import kotlinx.android.synthetic.main.fragment_help_screen.view.*
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.Default
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main

class NewsFragment: Fragment() {
    val listType = object : TypeToken<List<EventDetails>>(){}.type
    var data: List<EventDetails> = ArrayList()
    var filteredData: List<EventDetails> = ArrayList()
    val newsAdapter = NewsAdapter()
    val EVENTS_INFORMATION = "events_information"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        CoroutineScope(IO).launch {
            data = fileWorksThread(requireContext(),listType,EVENTS_INFORMATION).filterIsInstance<EventDetails>()
            filteredData = filterNews(data)
            Log.d("filteredData: ", filteredData.toString())
        }
    }
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_news_screen, container, false)
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
        }
        else {
            newsData.filter{it.eventCategory == filter}
        }
        return filteredNews
    }
}