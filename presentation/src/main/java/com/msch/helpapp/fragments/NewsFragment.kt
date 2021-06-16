package com.msch.helpapp.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.msch.data.model.EventDetails
import com.msch.helpapp.R
import com.msch.helpapp.presenters.NewsPresenter
import com.msch.helpapp.views.NewsView
import com.msch.helpapp.adapters.NewsAdapter
import durdinapps.rxfirebase2.DataSnapshotMapper
import durdinapps.rxfirebase2.RxFirebaseDatabase
import io.reactivex.SingleObserver
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_news_screen.*
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter

class NewsFragment : MvpAppCompatFragment(), NewsView {
    private val id = "categoryID"

    @InjectPresenter
    lateinit var newsPresenter: NewsPresenter

    @ProvidePresenter
    fun providePresenter(): NewsPresenter {
        return NewsPresenter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val filter = arguments?.getString(id).toString()
        val view = inflater.inflate(R.layout.fragment_news_screen, container, false)
        providePresenter()
        val data = newsPresenter.getNews(filter)
        newsPresenter.displayNews(data)
        switchLoadingScreen(view)
        return view
    }

    override fun showNews(news: List<EventDetails>) {
        val newsAdapter = NewsAdapter()
        this.recycler_view.layoutManager = LinearLayoutManager(requireContext())
        this.recycler_view.adapter = newsAdapter
        newsAdapter.submitList(news)
    }

    private fun switchLoadingScreen(view: View) {
        view.findViewById<FrameLayout>(R.id.nf_loadingScreen).visibility = GONE
    }
}