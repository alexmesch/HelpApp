package com.msch.helpapp

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.msch.helpapp.presenters.MainViewPresenter
import com.msch.helpapp.views.FragmentView
import com.msch.helpapp.fragments.*
import kotlinx.android.synthetic.main.ac_main_screen.*
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter

class MainActivity : MvpAppCompatActivity(), FragmentView{
    private lateinit var auth: FirebaseAuth

    @InjectPresenter(presenterId = "mainViewPresenter", tag = "")
    lateinit var mainViewPresenter: MainViewPresenter

    @ProvidePresenter
    fun provideMainViewPresenter(): MainViewPresenter {
        return MainViewPresenter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ac_main_screen)
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        provideMainViewPresenter()
        auth = mainViewPresenter.fbAuth()
        mainViewPresenter.showFragment(HelpFragment(), this.supportFragmentManager )
    }

    private val mOnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.news_button -> mainViewPresenter.showFragment(NewsFragment(),this.supportFragmentManager)
                R.id.search_button -> mainViewPresenter.showFragment(SearchFragment(),this.supportFragmentManager)
                R.id.profile_button -> {
                    if (auth.currentUser != null) {
                        mainViewPresenter.showFragment(ProfileFragment(),this.supportFragmentManager)
                    } else {
                        mainViewPresenter.showFragment(AuthFragment(),this.supportFragmentManager)
                    }
                }
                R.id.help_ghost_button -> mainViewPresenter.showFragment(HelpFragment(),this.supportFragmentManager)
                R.id.history_button -> {
                }
                else -> null
            } != null
        }
}