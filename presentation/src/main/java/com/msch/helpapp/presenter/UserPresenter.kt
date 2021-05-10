package com.msch.helpapp.presenter

import com.msch.helpapp.DI.DependencyInjector
import com.msch.helpapp.data.datasource.UserInfoDS
import com.msch.helpapp.domain.UserProfile
import com.msch.helpapp.view.contracts.UserInfoContract

class UserPresenter(view: UserInfoContract.UserView, dependencyInjector: DependencyInjector) :
    UserInfoContract.UserPresenter {
    private val userInfo: UserInfoDS = dependencyInjector.userInfo()
    private var view: UserInfoContract.UserView? = view

    override fun getUserInfo(uid: String): UserProfile {
        return userInfo.getUserInfo(uid)
    }

    override fun onDestroy() {
        this.view = null
    }
}