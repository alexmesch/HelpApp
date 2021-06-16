package com.msch.helpapp.presenters

import com.msch.data.model.EventDetails
import com.msch.helpapp.dagger.components.DaggerDataComponent
import com.msch.helpapp.views.EventDetailsView
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class EventDetailsPresenter: MvpPresenter<EventDetailsView>() {
    private val edComponent = DaggerDataComponent.builder().build().getEDComponent().eventDetailsDS()

    fun displayEvents(events: List<EventDetails>) {
        viewState.displayEvents(events)
        return
    }

    fun getEvents(): List<EventDetails> {
        return edComponent.download()
    }
}