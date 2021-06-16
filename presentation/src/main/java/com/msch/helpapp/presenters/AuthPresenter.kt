package com.msch.helpapp.presenters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.msch.helpapp.dagger.components.DaggerFirebaseComponent
import com.msch.helpapp.dagger.components.DaggerFragmentManagerComponent
import com.msch.helpapp.views.AuthView
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class AuthPresenter: MvpPresenter<AuthView>() {
    private val fb = DaggerFirebaseComponent.builder().build().provideFbOps().fbOps()
    private val fragmentManager = DaggerFragmentManagerComponent.builder().build().getFragmentManager().fm()

    fun performLogin(email: String, password: String): Boolean {
        return fb.firebaseLogin(email, password)
    }

    fun performReg(email: String, password: String): Boolean {
        return fb.firebaseReg(email, password)
    }

    fun displayProfile(authResult: Boolean, fm: FragmentManager) {
        viewState.showProfile(authResult, fm)
        return
    }

    fun showFragment(fragment: Fragment, fm: FragmentManager) {
        fragmentManager.openFragment(fragment, fm)
        return
    }
}