package com.msch.helpapp.presenters

import androidx.fragment.app.FragmentManager
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.msch.domain.model.UserProfile
import com.msch.helpapp.dagger.components.DaggerDataComponent
import com.msch.helpapp.dagger.components.DaggerFragmentManagerComponent
import com.msch.helpapp.views.UserView
import com.msch.helpapp.fragments.AuthFragment
import io.reactivex.Single
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class UserPresenter: MvpPresenter<UserView>() {
    private val udComponent = DaggerDataComponent.builder().build().getUDComponent().userInfoDS()
    private val fragmentManager = DaggerFragmentManagerComponent.builder().build().getFragmentManager().fm()

    fun logOut(fm: FragmentManager) {
        Firebase.auth.signOut()
        fm.popBackStack()
        fragmentManager.openFragment(AuthFragment(), fm)
    }

    fun showProfile(profile: com.msch.domain.model.UserProfile) {
        viewState.displayProfile(profile)
        return
    }

    fun getObservable(): Single<UserProfile> {
        return udComponent.getUserObservable(Firebase.auth.currentUser!!.uid)
    }
}