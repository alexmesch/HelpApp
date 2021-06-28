package com.msch.helpapp.presenters

import com.msch.domain.model.EventDetails
import com.msch.helpapp.dagger.components.DaggerDataComponent
import com.msch.helpapp.views.NewsView
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class NewsPresenter: MvpPresenter<NewsView>() {
    private val edComponent = DaggerDataComponent.builder().build().getEDComponent().eventDetailsDS()
    private val disposables = CompositeDisposable()
    private val observable = edComponent.getEdObservable()
    private var data: List<EventDetails> = ArrayList()

    fun getObservable(): Single<List<EventDetails>> {
        return edComponent.getEdObservable()
    }

    fun displayNews(news: List<EventDetails>) {
        viewState.showNews(news)
    }
}