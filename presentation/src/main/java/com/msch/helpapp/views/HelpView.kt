package com.msch.helpapp.views

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(value = AddToEndStrategy::class)
interface HelpView: MvpView {
    fun displayCategories(categoryItems: List<com.msch.domain.model.CategoryItems>)
}