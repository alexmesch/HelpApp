package com.msch.helpapp

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.msch.helpapp.dagger.components.DaggerActivityComponent
import com.msch.helpapp.dagger.modules.NavigationModule
import com.msch.helpapp.presenters.MainViewPresenter
import com.msch.helpapp.views.FragmentView
import com.msch.helpapp.fragments.*
import kotlinx.android.synthetic.main.ac_main_screen.*
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject

class MainActivity : MvpAppCompatActivity(), FragmentView {
    private lateinit var auth: FirebaseAuth

    @field: InjectPresenter
    @get: ProvidePresenter
    @Inject
    lateinit var mainViewPresenter: MainViewPresenter

    override fun onCreate(savedInstanceState: Bundle?) {

        DaggerActivityComponent
            .builder()
            .navigationModule(NavigationModule())
            .build()
            .inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.ac_main_screen)
        bottomNavigationView.setOnItemSelectedListener(mOnNavigationItemSelectedListener)
        auth = mainViewPresenter.fbAuth()
        mainViewPresenter.showFragment(HelpFragment(), this.supportFragmentManager )
    }

    private val mOnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener  { item ->
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