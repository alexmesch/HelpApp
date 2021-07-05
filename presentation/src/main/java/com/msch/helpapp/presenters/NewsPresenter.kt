package com.msch.helpapp.presenters

import com.msch.data.datasource.EventDetailsDS
import com.msch.domain.model.EventDetails
import com.msch.helpapp.views.NewsView
import io.reactivex.Single
import moxy.InjectViewState
import moxy.MvpPresenter
import javax.inject.Inject

@InjectViewState
class NewsPresenter @Inject constructor(private var ed: EventDetailsDS) : MvpPresenter<NewsView>() {
    fun getObservable(): Single<List<EventDetails>> {
        return ed.getEdObservable()
    }

    fun displayNews(news: List<EventDetails>) {
        viewState.showNews(news)
    }
}