package com.msch.helpapp

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.msch.helpapp.view.fragments.*
import kotlinx.android.synthetic.main.ac_main_screen.*
import com.msch.helpapp.view.fragments.FragmentsManager.openFragment

class MainActivity : AppCompatActivity() {
    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ac_main_screen)
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        auth = Firebase.auth
        openFragment(HelpFragment(), this.supportFragmentManager)
    }

    fun openHelpScreen(v: View) {
        openFragment(HelpFragment(), this.supportFragmentManager)
    }

    private val mOnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.news_button -> openFragment(NewsFragment(), this.supportFragmentManager)
                R.id.search_button -> openFragment(SearchFragment(), this.supportFragmentManager)
                R.id.profile_button -> {
                    if (auth.currentUser != null) {
                        openFragment(ProfileFragment(), this.supportFragmentManager)
                    } else {
                        openFragment(AuthFragment(), this.supportFragmentManager)
                    }
                }
                R.id.help_ghost_button -> openFragment(HelpFragment(), this.supportFragmentManager)
                R.id.history_button -> {
                }
                else -> null
            } != null
        }
}