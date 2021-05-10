package com.msch.helpapp.view.contracts

import com.msch.helpapp.domain.EventDetails
import com.msch.helpapp.presenter.BasePresenter
import com.msch.helpapp.view.BaseView

interface EventsContract {
    interface EventsPresenter: BasePresenter {
        fun getEvents(filter: String): List<EventDetails>
    }

    interface EventsView: BaseView<EventsPresenter> {
        fun displayEvents(news: List<EventDetails>)
    }
}