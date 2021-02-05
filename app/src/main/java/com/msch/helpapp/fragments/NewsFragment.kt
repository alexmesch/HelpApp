package com.msch.helpapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.msch.helpapp.R
import com.msch.helpapp.adapters.NewsAdapter
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
        
        //val filter: String = "Взрослые"
        //val filtered = data.filter { it.eventCategory == filter }

        view.recycler_view.layoutManager = LinearLayoutManager(requireActivity())
        view.recycler_view.adapter = newsAdapter
        newsAdapter.submitList(data) //filtered

        return view
    }
}