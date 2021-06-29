package com.msch.helpapp.presenters

import com.msch.domain.model.EventDetails
import com.msch.helpapp.dagger.components.DaggerDataComponent
import com.msch.helpapp.dagger.components.DataComponent
import com.msch.helpapp.views.NewsView
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import moxy.InjectViewState
import moxy.MvpPresenter
import javax.inject.Inject

@InjectViewState
class NewsPresenter: MvpPresenter<NewsView>() {

    @Inject
    fun getObservable(edComponent: DataComponent): Single<List<EventDetails>> {
        return edComponent.getEDComponent().eventDetailsDS().getEdObservable()
    }

    fun displayNews(news: List<EventDetails>) {
        viewState.showNews(news)
    }
}