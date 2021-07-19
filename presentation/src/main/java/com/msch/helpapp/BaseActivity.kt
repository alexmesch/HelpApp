package com.msch.helpapp

import android.app.Activity
import android.os.Bundle
import com.msch.helpapp.dagger.components.ApplicationComponent
import com.msch.helpapp.dagger.modules.NavigationModule
import javax.inject.Inject

abstract class BaseActivity: Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.getApplicationComponent()?.inject(this)
    }

    private fun getApplicationComponent(): ApplicationComponent? {
        return (application as AndroidApplication).getAppComponent()
    }

}