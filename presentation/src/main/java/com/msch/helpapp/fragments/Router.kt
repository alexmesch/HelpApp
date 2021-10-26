package com.msch.helpapp.fragments

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.transition.FragmentTransitionSupport
import com.msch.helpapp.R
import javax.inject.Inject


class Router @Inject constructor(private var fm: FragmentManager){
    fun openFragment(fragment: Fragment) {
        val transaction = fm.beginTransaction()
        transaction.replace(R.id.fragmentView, fragment)
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    fun popBackStack() {
        fm.popBackStack()
    }
}