package com.msch.helpapp.views

import com.msch.domain.model.UserProfile
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(value = AddToEndStrategy::class)
interface UserView: MvpView {
    fun displayProfile(profile: UserProfile)
}