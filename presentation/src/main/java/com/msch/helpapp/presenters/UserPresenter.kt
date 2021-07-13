package com.msch.helpapp.presenters

import androidx.fragment.app.FragmentManager
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.msch.domain.interactor.GetUsersUseCase
import com.msch.domain.model.UserProfile
import com.msch.helpapp.views.UserView
import com.msch.helpapp.fragments.AuthFragment
import com.msch.helpapp.fragments.FragmentsManager
import io.reactivex.Single
import moxy.InjectViewState
import moxy.MvpPresenter
import javax.inject.Inject

@InjectViewState
class UserPresenter @Inject constructor(
    private var fragmentManager: FragmentsManager,
    private var ud: GetUsersUseCase
) : MvpPresenter<UserView>() {
    fun logOut(fm: FragmentManager) {
        Firebase.auth.signOut()
        fm.popBackStack()
        fragmentManager.openFragment(AuthFragment(), fm)
    }

    fun showProfile(profile: UserProfile) {
        viewState.displayProfile(profile)
        return
    }

    fun getObservable(): Single<UserProfile> {
        return ud.execute(Firebase.auth.currentUser!!.uid)
    }
}