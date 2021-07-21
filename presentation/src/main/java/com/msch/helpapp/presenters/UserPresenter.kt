package com.msch.helpapp.presenters

import androidx.fragment.app.FragmentManager
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.msch.domain.interactor.GetUsersUseCase
import com.msch.domain.model.UserProfile
import com.msch.helpapp.views.UserView
import com.msch.helpapp.fragments.AuthFragment
import com.msch.helpapp.fragments.Router
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import moxy.InjectViewState
import moxy.MvpPresenter
import javax.inject.Inject

@InjectViewState
class UserPresenter @Inject constructor(
    private var fragmentManager: Router,
    private var ud: GetUsersUseCase
) : MvpPresenter<UserView>() {

    private var disposables = CompositeDisposable()

    fun showProfile() {
        getUserSingle().subscribe({
            displayProfile(it)
        }, {
            it.stackTrace
        })
            .let {disposables.add(it)}
    }

    fun logOut(fm: FragmentManager) {
        Firebase.auth.signOut()
        fm.popBackStack()
        fragmentManager.openFragment(AuthFragment(), fm)
    }

    private fun displayProfile(profile: UserProfile) {
        viewState.displayProfile(profile)
        return
    }

    private fun getUserSingle(): Single<UserProfile> {
        return ud.execute(Firebase.auth.currentUser!!.uid)
    }

    override fun onDestroy() {
        super.onDestroy()
        disposables.clear()
    }
}