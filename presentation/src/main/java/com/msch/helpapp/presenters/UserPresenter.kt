package com.msch.helpapp.presenters

import androidx.fragment.app.FragmentManager
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.msch.data.network.FirebaseOps
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
    private val router: Router,
    private val usersUseCase: GetUsersUseCase,
    private val firebaseOps: FirebaseOps
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

    fun logOut() {
        firebaseOps.signOut()
        router.popBackStack()
        router.openFragment(AuthFragment())
    }

    private fun displayProfile(profile: UserProfile) {
        viewState.displayProfile(profile)
        return
    }

    private fun getUserSingle(): Single<UserProfile> {
        return usersUseCase.execute(Firebase.auth.currentUser!!.uid)
    }

    override fun onDestroy() {
        super.onDestroy()
        disposables.clear()
    }
}