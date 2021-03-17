package com.msch.helpapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.reflect.TypeToken
import com.msch.helpapp.R
import com.msch.helpapp.adapters.NewsAdapter
import com.msch.helpapp.concurrency.FileCoroutine.fileWorksThread
import com.msch.helpapp.concurrency.FileCoroutine.logThread
import com.msch.helpapp.database.RealmConfig.realmConfig
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
        var filteredData: List<EventDetails> = ArrayList()
        val view = inflater.inflate(R.layout.fragment_news_screen, container, false)
        val newsAdapter = NewsAdapter()
        val loadingScreen: FrameLayout = view.findViewById(R.id.nf_loadingScreen)

        lifecycleScope.launch {
            withContext(IO) {
                val realm = Realm.getInstance(realmConfig)
                val realmData = realm.where(RealmEvents::class.java).findAll()
                data = parseJson(realmData.asJSON().toString(), listType).filterIsInstance<EventDetails>()
                //data = fileWorksThread(requireContext(), listType, EVENTS_INFORMATION).filterIsInstance<EventDetails>()
                filteredData = filterNews(data)
                realm.close()
            }
            logThread("UIMain")
            view.recycler_view.layoutManager = LinearLayoutManager(requireActivity())
            view.recycler_view.adapter = newsAdapter
            newsAdapter.submitList(filteredData)
            loadingScreen.visibility = GONE
        }
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