package com.msch.helpapp.presenters

import com.msch.domain.interactor.GetEventsUseCase
import com.msch.domain.model.EventDetails
import com.msch.helpapp.views.NewsView
import io.reactivex.Single
import moxy.InjectViewState
import moxy.MvpPresenter
import javax.inject.Inject

@InjectViewState
class NewsPresenter @Inject constructor(private var ed: GetEventsUseCase) : MvpPresenter<NewsView>() {
    fun getObservable(): Single<List<EventDetails>> {
        return ed.execute()
    }

    fun displayNews(news: List<EventDetails>) {
        viewState.showNews(news)
    }
}