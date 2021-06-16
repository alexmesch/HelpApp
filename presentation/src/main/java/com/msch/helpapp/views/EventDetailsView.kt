package com.msch.helpapp.views

import com.msch.data.model.EventDetails
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(value = AddToEndStrategy::class)
interface EventDetailsView: MvpView {
    fun displayEvents(events: List<EventDetails>)
}