package com.msch.helpapp

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.msch.helpapp.dagger.components.DaggerFragmentManagerComponent
import com.msch.helpapp.dagger.components.DataComponent
import com.msch.helpapp.presenters.MainViewPresenter
import com.msch.helpapp.views.FragmentView
import com.msch.helpapp.fragments.*
import kotlinx.android.synthetic.main.ac_main_screen.*
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject

class MainActivity : MvpAppCompatActivity(), FragmentView{
    private lateinit var auth: FirebaseAuth
    private val fmComponent = DaggerFragmentManagerComponent.create()

    @InjectPresenter(presenterId = "mainViewPresenter", tag = "")
    lateinit var mainViewPresenter: MainViewPresenter

    @ProvidePresenter
    fun provideMainViewPresenter(): MainViewPresenter {
        return MainViewPresenter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        provideMainViewPresenter()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ac_main_screen)
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        auth = mainViewPresenter.fbAuth()
        mainViewPresenter.showFragment(fmComponent, HelpFragment(), this.supportFragmentManager )
    }

    private val mOnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.news_button -> mainViewPresenter.showFragment(fmComponent, NewsFragment(),this.supportFragmentManager)
                R.id.search_button -> mainViewPresenter.showFragment(fmComponent, SearchFragment(),this.supportFragmentManager)
                R.id.profile_button -> {
                    if (auth.currentUser != null) {
                        mainViewPresenter.showFragment(fmComponent, ProfileFragment(),this.supportFragmentManager)
                    } else {
                        mainViewPresenter.showFragment(fmComponent, AuthFragment(),this.supportFragmentManager)
                    }
                }
                R.id.help_ghost_button -> mainViewPresenter.showFragment(fmComponent, HelpFragment(),this.supportFragmentManager)
                R.id.history_button -> {
                }
                else -> null
            } != null
        }
}