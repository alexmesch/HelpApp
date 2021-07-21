package com.msch.helpapp.fragments

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.msch.helpapp.R
import javax.inject.Inject


class FragmentsManager @Inject constructor(var fm: FragmentManager){
    fun openFragment(fragment: Fragment) {
        val transaction = fm.beginTransaction()
        transaction.replace(R.id.fragmentView, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}