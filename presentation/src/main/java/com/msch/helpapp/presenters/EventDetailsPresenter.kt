package com.msch.helpapp.presenters

import com.msch.domain.model.EventDetails
import com.msch.helpapp.dagger.components.DataComponent
import com.msch.helpapp.views.EventDetailsView
import io.reactivex.Single
import moxy.InjectViewState
import moxy.MvpPresenter
import javax.inject.Inject

@InjectViewState
class EventDetailsPresenter: MvpPresenter<EventDetailsView>() {
    fun displayEvents(events: List<EventDetails>) {
        viewState.displayEvents(events)
        return
    }

    @Inject
    fun getObservable(edComponent: DataComponent): Single<List<EventDetails>> {
        return edComponent.getEDComponent().eventDetailsDS().getEdObservable()
    }
}