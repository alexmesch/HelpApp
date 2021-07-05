package com.msch.helpapp.presenters

import com.msch.data.datasource.EventDetailsDS
import com.msch.domain.model.EventDetails
import com.msch.helpapp.views.EventDetailsView
import io.reactivex.Single
import moxy.InjectViewState
import moxy.MvpPresenter
import javax.inject.Inject

@InjectViewState
class EventDetailsPresenter
@Inject constructor(private var ed: EventDetailsDS):
    MvpPresenter<EventDetailsView>() {
    fun displayEvents(events: List<EventDetails>) {
        viewState.displayEvents(events)
        return
    }

    fun getObservable(): Single<List<EventDetails>> {
        return ed.getEdObservable()
    }
}