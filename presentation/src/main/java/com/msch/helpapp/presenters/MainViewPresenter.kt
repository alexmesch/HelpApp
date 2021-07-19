package com.msch.helpapp.presenters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.msch.helpapp.fragments.FragmentsManager
import com.msch.helpapp.views.FragmentView
import moxy.InjectViewState
import moxy.MvpPresenter
import javax.inject.Inject

@InjectViewState
class MainViewPresenter @Inject constructor(
    //private var fragmentManager: FragmentsManager
    )
    : MvpPresenter<FragmentView>() {
    fun showFragment(fragment: Fragment, fm: FragmentManager) {
        //fragmentManager.openFragment(fragment, fm)
        FragmentsManager().openFragment(fragment, fm)
        return
    }

    fun fbAuth(): FirebaseAuth {
        return Firebase.auth
    }
}