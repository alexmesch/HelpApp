package com.msch.helpapp

import com.msch.helpapp.dagger.components.ActivityComponent
import com.msch.helpapp.dagger.components.ApplicationComponent
import com.msch.helpapp.dagger.components.DaggerActivityComponent
import com.msch.helpapp.dagger.modules.NavigationModule
import moxy.MvpAppCompatActivity

abstract class BaseActivity: MvpAppCompatActivity() {
    private fun getApplicationComponent(): ApplicationComponent? {
        return (application as AndroidApplication).getAppComponent()
    }

    fun initializeInjector(): ActivityComponent {
        return DaggerActivityComponent.builder()
            .applicationComponent(getApplicationComponent())
            .navigationModule(NavigationModule(supportFragmentManager))
            .build()
    }
}