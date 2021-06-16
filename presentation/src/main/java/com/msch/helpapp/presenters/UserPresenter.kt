package com.msch.helpapp.presenters

import androidx.fragment.app.FragmentManager
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.msch.data.model.UserProfile
import com.msch.helpapp.dagger.components.DaggerDataComponent
import com.msch.helpapp.dagger.components.DaggerFragmentManagerComponent
import com.msch.helpapp.views.UserView
import com.msch.helpapp.fragments.AuthFragment
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class UserPresenter: MvpPresenter<UserView>() {
    private val udComponent = DaggerDataComponent.builder().build().getUDComponent().userInfoDS()
    private val fragmentManager = DaggerFragmentManagerComponent.builder().build().getFragmentManager().fm()

    fun getUserInfo(): UserProfile {
        return udComponent.getUserInfo(Firebase.auth.currentUser!!.uid)
    }

    fun logOut(fm: FragmentManager) {
        Firebase.auth.signOut()
        fm.popBackStack()
        fragmentManager.openFragment(AuthFragment(), fm)
    }

    fun showProfile(profile: UserProfile) {
        viewState.displayProfile(profile)
        return
    }
}