package com.msch.helpapp.presenters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.firebase.auth.AuthResult
import com.msch.helpapp.dagger.components.DaggerDataComponent
import com.msch.helpapp.dagger.components.DaggerFirebaseComponent
import com.msch.helpapp.dagger.components.DaggerFragmentManagerComponent
import com.msch.helpapp.dagger.modules.FirebaseModule
import com.msch.helpapp.dagger.modules.FragmentManagerModule
import com.msch.helpapp.views.AuthView
import io.reactivex.Single
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class AuthPresenter: MvpPresenter<AuthView>() {
    private val fbOperations = DaggerFirebaseComponent.builder().build().provideFbOps().fbOps()
    private val fragmentManager = DaggerFragmentManagerComponent.builder().build().getFragmentManager().fm()

    fun displayProfile(authResult: Boolean, fm: FragmentManager) {
        viewState.showProfile(authResult, fm)
        return
    }

    fun showFragment(fragment: Fragment, fm: FragmentManager) {
        fragmentManager.openFragment(fragment, fm)
        return
    }

    fun getSignInObservable(email: String, password: String): Single<AuthResult> {
        return fbOperations.getLoginObservable(email, password)
    }

    fun getSignUpObservable(email: String, password: String): Single<AuthResult> {
        return fbOperations.getRegisterObservable(email, password)
    }
}