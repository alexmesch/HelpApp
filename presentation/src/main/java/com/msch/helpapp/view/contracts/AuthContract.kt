package com.msch.helpapp.view.contracts

import com.msch.helpapp.presenter.BasePresenter
import com.msch.helpapp.view.BaseView

interface AuthContract {
    interface AuthPresenter: BasePresenter {
        fun performLogin(email: String, password: String): Boolean
        fun performReg(email: String, password: String): Boolean
    }

    interface AuthView: BaseView<AuthPresenter> {
        fun displayProfile(authResult: Boolean)
    }
}