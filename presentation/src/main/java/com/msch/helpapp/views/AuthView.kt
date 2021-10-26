package com.msch.helpapp.views

import androidx.fragment.app.FragmentManager
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(value = AddToEndStrategy::class)
interface AuthView: MvpView {
    fun showProfile(authResult: Boolean, fm: FragmentManager)
}