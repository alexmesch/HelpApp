package com.msch.helpapp.presenters

import android.util.Log
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
@Inject constructor(private val eventsUseCase: GetEventsUseCase) :
    MvpPresenter<EventDetailsView>() {
    private var disposables = CompositeDisposable()

    private fun displayEvents(events: List<EventDetails>) {
        viewState.displayEvents(events)
    }

    fun loadEvents() {
        getEventsSingle().subscribe({
            displayEvents(it)
        }, {
            Log.e("epfObserver", "subscription fail!")
            it.printStackTrace()
        })
            .let { disposables.add(it) }
    }

    private fun getEventsSingle(): Single<List<EventDetails>> {
        return eventsUseCase.execute()
    }

    override fun onDestroy() {
        super.onDestroy()
        disposables.clear()
    }
}