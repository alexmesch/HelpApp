package com.msch.helpapp.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.msch.helpapp.DI.DependencyInjectorImpl
import com.msch.helpapp.R
import com.msch.helpapp.domain.EventDetails
import com.msch.helpapp.presenter.EventsPresenter
import com.msch.helpapp.view.adapters.NewsAdapter
import com.msch.helpapp.view.contracts.EventsContract
import kotlinx.android.synthetic.main.fragment_news_screen.*
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO

class NewsFragment : Fragment(), EventsContract.EventsView {
    private val lifecycleScope = MainScope()
    private lateinit var presenter: EventsContract.EventsPresenter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        lateinit var data: List<EventDetails>
        val filter = arguments?.getString("categoryID").toString()
        val view = inflater.inflate(R.layout.fragment_news_screen, container, false)

        setPresenter(EventsPresenter(this, DependencyInjectorImpl()))
        lifecycleScope.launch {
            withContext(IO) {
                data = presenter.getEvents(filter)
            }
            displayEvents(data)
            switchLoadingScreen(view)
        }
        return view
    }

    override fun onDestroy() {
        presenter.onDestroy()
        lifecycleScope.cancel()
        super.onDestroy()
    }

    override fun setPresenter(presenter: EventsContract.EventsPresenter) {
        this.presenter = presenter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        onDestroy()
        lifecycleScope.cancel()
    }

    override fun displayEvents(news: List<EventDetails>) {
        val newsAdapter = NewsAdapter()
        this.recycler_view.layoutManager = LinearLayoutManager(requireContext())
        this.recycler_view.adapter = newsAdapter
        newsAdapter.submitList(news)
    }

    private fun switchLoadingScreen(view: View) {
        view.findViewById<FrameLayout>(R.id.nf_loadingScreen).visibility = GONE
    }
}