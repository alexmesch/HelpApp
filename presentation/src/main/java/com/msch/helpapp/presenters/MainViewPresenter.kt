package com.msch.helpapp.presenters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.msch.helpapp.dagger.components.DaggerFragmentManagerComponent
import com.msch.helpapp.views.FragmentView
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class MainViewPresenter: MvpPresenter<FragmentView>() {
    private val fragmentManager = DaggerFragmentManagerComponent.builder().build().getFragmentManager().fm()

    fun showFragment(fragment: Fragment, fm: FragmentManager) {
        //viewState.openFragment(fragment, fm)
        fragmentManager.openFragment(fragment, fm)
        return
    }

    fun fbAuth(): FirebaseAuth {
        return Firebase.auth
    }
}