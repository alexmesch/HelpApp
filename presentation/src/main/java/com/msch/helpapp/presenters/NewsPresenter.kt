package com.msch.helpapp.presenters

import com.msch.data.model.EventDetails
import com.msch.helpapp.dagger.components.DaggerDataComponent
import com.msch.helpapp.views.NewsView
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class NewsPresenter: MvpPresenter<NewsView>() {
    private val edComponent = DaggerDataComponent.builder().build().getEDComponent().eventDetailsDS()
    fun getNews(filter: String): List<EventDetails> {
        return if(filter == "null") {
            edComponent.download()
        }
        else {
            edComponent.download().filter{ it.eventCategory == filter}
        }
    }

    fun displayNews(news: List<EventDetails>) {
        viewState.showNews(news)
    }
}