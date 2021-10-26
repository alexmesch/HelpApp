package com.msch.helpapp.presenters

import android.util.Log
import com.msch.domain.interactor.GetEventsUseCase
import com.msch.domain.model.EventDetails
import com.msch.helpapp.views.SearchView
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import moxy.MvpPresenter
import javax.inject.Inject

class SearchPresenter @Inject constructor(private val eventsUseCase: GetEventsUseCase) :
    MvpPresenter<SearchView>() {

    private var disposables = CompositeDisposable()

    fun showAllEvents(){
        getNewsSingle().subscribe({ list ->
            displayEvents(list)
        }, {
            Log.e("spObserver", "subscription fail!")
            it.printStackTrace()
        })
            .let { disposables.add(it) }
    }

    private fun displayEvents(list: List<EventDetails>) {
        viewState.showEvents(list)
    }

    private fun getNewsSingle(): Single<List<EventDetails>> {
        return eventsUseCase.execute()
    }

    override fun onDestroy() {
        super.onDestroy()
        disposables.clear()
    }
}