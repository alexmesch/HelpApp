package com.msch.helpapp.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.msch.domain.model.EventDetails
import com.msch.helpapp.R
import com.msch.helpapp.presenters.NewsPresenter
import com.msch.helpapp.views.NewsView
import com.msch.helpapp.adapters.NewsAdapter
import io.reactivex.SingleObserver
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.fragment_news_screen.*
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter

class NewsFragment : MvpAppCompatFragment(), NewsView {
    private val id = "categoryID"
    private val disposables = CompositeDisposable()

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
        val view = inflater.inflate(R.layout.fragment_news_screen, container, false)
        val filter = arguments?.getString(id).toString()

        providePresenter()

        newsPresenter.getObservable().subscribe(object: SingleObserver<List<EventDetails>> {
            override fun onSubscribe(d: Disposable) {
                disposables.add(d)
            }

            override fun onSuccess(t: List<EventDetails>) {
                if (filter == "null") {
                    newsPresenter.displayNews(t)
                }
                else {
                    newsPresenter.displayNews(t.filter {it.eventCategory == filter})
                }
                disposables.clear()
                view.findViewById<FrameLayout>(R.id.nf_loadingScreen).visibility = GONE
            }

            override fun onError(e: Throwable) {
                Log.e("nfObserver", "subscription fail!")
            }
        })
        return view
    }

    override fun showNews(news: List<EventDetails>) {
        val newsAdapter = NewsAdapter()
        this.recycler_view.layoutManager = LinearLayoutManager(requireContext())
        this.recycler_view.adapter = newsAdapter
        newsAdapter.submitList(news)
    }
}