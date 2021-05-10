package com.msch.helpapp.presenter

import com.msch.helpapp.DI.DependencyInjector
import com.msch.helpapp.data.datasource.EventDetailsDS
import com.msch.helpapp.domain.EventDetails
import com.msch.helpapp.view.contracts.EventsContract

class EventsPresenter(
    view: EventsContract.EventsView,
    dependencyInjector: DependencyInjector
) : EventsContract.EventsPresenter {
    private val eventDetailsDS: EventDetailsDS = dependencyInjector.eventDs()
    private var view: EventsContract.EventsView? = view

    private fun loadEvents(): List<EventDetails> {
        return eventDetailsDS.download()
    }

    override fun onDestroy() {
        this.view = null
    }

    override fun getEvents(filter: String): List<EventDetails> {
        return if(filter == "null") {
            loadEvents()
        }
        else {
            loadEvents().filter{ it.eventCategory == filter}
        }
    }
}