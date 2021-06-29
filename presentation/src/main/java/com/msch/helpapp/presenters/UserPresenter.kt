package com.msch.helpapp.presenters

import androidx.fragment.app.FragmentManager
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.msch.domain.model.UserProfile
import com.msch.helpapp.dagger.components.*
import com.msch.helpapp.views.UserView
import com.msch.helpapp.fragments.AuthFragment
import io.reactivex.Single
import moxy.InjectViewState
import moxy.MvpPresenter
import javax.inject.Inject

@InjectViewState
class UserPresenter: MvpPresenter<UserView>() {
    @Inject
    fun logOut(fmComponent: FragmentManagerComponent, fm: FragmentManager) {
        Firebase.auth.signOut()
        fm.popBackStack()
        fmComponent.getFragmentManager().fm().openFragment(AuthFragment(), fm)
    }

    fun showProfile(profile: UserProfile) {
        viewState.displayProfile(profile)
        return
    }

    @Inject
    fun getObservable(udComponent: DataComponent): Single<UserProfile> {
        return udComponent.getUDComponent().userInfoDS().getUserObservable(Firebase.auth.currentUser!!.uid)
    }
}