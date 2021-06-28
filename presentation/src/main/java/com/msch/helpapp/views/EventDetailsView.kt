package com.msch.helpapp.views

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(value = AddToEndStrategy::class)
interface EventDetailsView: MvpView {
    fun displayEvents(events: List<com.msch.domain.model.EventDetails>)
}