package com.msch.helpapp.presenters

import android.util.Log
import com.msch.domain.interactor.GetEventsUseCase
import com.msch.domain.model.EventDetails
import com.msch.helpapp.views.NewsView
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import moxy.InjectViewState
import moxy.MvpPresenter
import javax.inject.Inject

@InjectViewState
class NewsPresenter @Inject constructor(private var ed: GetEventsUseCase) :
    MvpPresenter<NewsView>() {

    private var disposables = CompositeDisposable()

    fun showNews(filter: String) {
        getNewsSingle().subscribe({ list ->
            if (filter == "null") {
                displayNews(list)
            }
            else {
                displayNews(list.filter {it.eventCategory == filter})
            }
        }, {
            Log.e("nfObserver", "subscription fail!")
            it.stackTrace
        })
            .let { disposables.add(it) }
    }

    private fun getNewsSingle(): Single<List<EventDetails>> {
        return ed.execute()
    }

    fun displayNews(news: List<EventDetails>) {
        viewState.showNews(news)
    }

    override fun onDestroy() {
        super.onDestroy()
        disposables.clear()
    }
}