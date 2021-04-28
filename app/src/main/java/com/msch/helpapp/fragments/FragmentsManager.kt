package com.msch.helpapp.fragments
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.msch.helpapp.R

object FragmentsManager {
    fun openFragment(fragment: Fragment, fm: FragmentManager) {
        val transaction = fm.beginTransaction()
        transaction.replace(R.id.fragmentView, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}