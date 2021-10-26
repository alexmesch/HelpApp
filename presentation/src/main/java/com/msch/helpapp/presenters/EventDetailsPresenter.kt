package com.msch.helpapp.presenters

import com.msch.domain.interactor.GetEventsUseCase
import com.msch.domain.model.EventDetails
import com.msch.helpapp.views.EventDetailsView
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import moxy.InjectViewState
import moxy.MvpPresenter
import javax.inject.Inject

@InjectViewState
class EventDetailsPresenter
@Inject constructor(private val ed: GetEventsUseCase) :
    MvpPresenter<EventDetailsView>() {
    private var disposables = CompositeDisposable()

    private fun displayEvents(events: List<EventDetails>) {
        viewState.displayEvents(events)
        return
    }

    fun showEvents() {
        getEventsSingle().subscribe({
            displayEvents(it)
        }, {
            it.stackTrace
        })
            .let { disposables.add(it) }
    }

    private fun getEventsSingle(): Single<List<EventDetails>> {
        return ed.execute()
    }

    override fun onDestroy() {
        super.onDestroy()
        disposables.clear()
    }
}