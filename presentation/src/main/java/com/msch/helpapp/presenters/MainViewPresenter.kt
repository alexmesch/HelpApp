package com.msch.helpapp.presenters

import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.msch.data.network.FirebaseOps
import com.msch.helpapp.fragments.Router
import com.msch.helpapp.views.FragmentView
import moxy.InjectViewState
import moxy.MvpPresenter
import javax.inject.Inject

@InjectViewState
class MainViewPresenter @Inject constructor(
    private val router: Router,
    private val firebaseOps: FirebaseOps
    )
    : MvpPresenter<FragmentView>() {

    fun showFragment(fragment: Fragment) {
        router.openFragment(fragment)
    }

    fun fbAuth(): FirebaseAuth {
        return firebaseOps.auth()
    }
}