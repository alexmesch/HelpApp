package com.msch.helpapp

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.msch.helpapp.fragments.HelpFragment
import com.msch.helpapp.fragments.ProfileFragment
import com.msch.helpapp.fragments.SearchFragment
import kotlinx.android.synthetic.main.ac_main_screen.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ac_main_screen)

        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        openFragment(HelpFragment())

    }

    fun openHelpScreen(v: View) {
        openFragment(HelpFragment())
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.news_button -> openFragment(HelpFragment())
            R.id.search_button -> openFragment(SearchFragment())
            R.id.profile_button -> openFragment(ProfileFragment())
            R.id.history_button -> {}
            else -> null
        } != null
    }

    private fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentView, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}