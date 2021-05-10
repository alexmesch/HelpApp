package com.msch.helpapp.presenter

import com.msch.helpapp.DI.DependencyInjector
import com.msch.helpapp.view.contracts.AuthContract

class AuthPresenter(view: AuthContract.AuthView, dependencyInjector: DependencyInjector): AuthContract.AuthPresenter {
    private val login = dependencyInjector.login()
    private val register = dependencyInjector.register()
    private var view: AuthContract.AuthView? = view

    override fun performLogin(email: String, password: String): Boolean {
        return login.firebaseLogin(email, password)
    }

    override fun performReg(email: String,password: String): Boolean {
        return register.firebaseReg(email, password)
    }

    override fun onDestroy() {
        this.view = null
    }
}