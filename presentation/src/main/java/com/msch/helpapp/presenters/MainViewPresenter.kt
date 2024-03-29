package com.msch.helpapp.presenters

import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.msch.helpapp.fragments.Router
import com.msch.helpapp.views.FragmentView
import moxy.InjectViewState
import moxy.MvpPresenter
import javax.inject.Inject

@InjectViewState
class MainViewPresenter @Inject constructor(
    private var fragmentManager: Router
    )
    : MvpPresenter<FragmentView>() {
    fun showFragment(fragment: Fragment) {
        fragmentManager.openFragment(fragment)
        return
    }

    fun fbAuth(): FirebaseAuth {
        return Firebase.auth
    }
}