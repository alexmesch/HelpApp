package com.msch.helpapp.presenters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.firebase.auth.AuthResult
import com.msch.helpapp.dagger.components.*
import com.msch.helpapp.views.AuthView
import io.reactivex.Single
import moxy.InjectViewState
import moxy.MvpPresenter
import javax.inject.Inject

@InjectViewState
class AuthPresenter @Inject constructor(private var fmComponent: FragmentManagerComponent, private var fbOpsComponent: FirebaseComponent) : MvpPresenter<AuthView>() {
    fun displayProfile(authResult: Boolean, fm: FragmentManager) {
        viewState.showProfile(authResult, fm)
        return
    }

    fun showFragment(fragment: Fragment, fm: FragmentManager) {
        fmComponent.getFragmentManager().fm().openFragment(fragment, fm)
        return
    }

    fun getSignInObservable(email: String, password: String): Single<AuthResult> {
        return fbOpsComponent.provideFbOps().fbOps().getLoginObservable(email, password)
    }

    fun getSignUpObservable(email: String, password: String): Single<AuthResult> {
        return fbOpsComponent.provideFbOps().fbOps().getRegisterObservable(email, password)
    }
}