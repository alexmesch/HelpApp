package com.msch.helpapp.view.contracts

import com.msch.helpapp.domain.UserProfile
import com.msch.helpapp.presenter.BasePresenter
import com.msch.helpapp.view.BaseView

interface UserInfoContract {
    interface UserPresenter: BasePresenter {
        fun getUserInfo(uid: String): UserProfile

    }

    interface UserView: BaseView<UserPresenter> {
        fun displayProfile(profile: UserProfile)
    }
}