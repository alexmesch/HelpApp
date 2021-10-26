package com.msch.helpapp

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.msch.helpapp.dagger.HasComponent
import com.msch.helpapp.dagger.components.ActivityComponent
import com.msch.helpapp.dagger.components.FragmentComponent
import com.msch.helpapp.fragments.*
import com.msch.helpapp.presenters.MainViewPresenter
import com.msch.helpapp.views.FragmentView
import kotlinx.android.synthetic.main.ac_main_screen.*
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject

class MainActivity : BaseActivity(), FragmentView, HasComponent<FragmentComponent> {
    private lateinit var auth: FirebaseAuth
    private var activityComponent: ActivityComponent? = null

    @field: InjectPresenter
    @get: ProvidePresenter
    @Inject
    lateinit var mainViewPresenter: MainViewPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        if (!::mainViewPresenter.isInitialized) {
            activityComponent = initializeInjector()
            activityComponent?.inject(this)
        }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ac_main_screen)
        bottomNavigationView.setOnItemSelectedListener(mOnNavigationItemSelectedListener)
        auth = mainViewPresenter.fbAuth()
        mainViewPresenter.showFragment(HelpFragment())
    }

    private val mOnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.news_button -> mainViewPresenter.showFragment(
                    NewsFragment(),
                )
                R.id.search_button -> mainViewPresenter.showFragment(SearchFragment())
                R.id.profile_button -> {
                    if (auth.currentUser != null) {
                        mainViewPresenter.showFragment(ProfileFragment())
                    } else {
                        mainViewPresenter.showFragment(AuthFragment())
                    }
                }
                R.id.help_ghost_button -> mainViewPresenter.showFragment(HelpFragment())
                R.id.history_button -> {
                }
                else -> null
            } != null
        }

    override fun getComponent(): ActivityComponent? {
        return this.activityComponent
    }
}