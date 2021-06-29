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
class AuthPresenter: MvpPresenter<AuthView>() {
    fun displayProfile(authResult: Boolean, fm: FragmentManager) {
        viewState.showProfile(authResult, fm)
        return
    }

    @Inject
    fun showFragment(fmComponent: FragmentManagerComponent,fragment: Fragment, fm: FragmentManager) {
        fmComponent.getFragmentManager().fm().openFragment(fragment, fm)
        return
    }

    @Inject
    fun getSignInObservable(fbOpsComponent: FirebaseComponent, email: String, password: String): Single<AuthResult> {
        return fbOpsComponent.provideFbOps().fbOps().getLoginObservable(email, password)
    }

    @Inject
    fun getSignUpObservable(fbOpsComponent: FirebaseComponent,email: String, password: String): Single<AuthResult> {
        return fbOpsComponent.provideFbOps().fbOps().getRegisterObservable(email, password)
    }
}