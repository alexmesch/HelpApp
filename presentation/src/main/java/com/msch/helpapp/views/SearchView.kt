package com.msch.helpapp.views

import com.msch.domain.model.EventDetails
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(value = AddToEndStrategy::class)
interface SearchView: MvpView {
    fun showEvents(events: List<EventDetails>)
}