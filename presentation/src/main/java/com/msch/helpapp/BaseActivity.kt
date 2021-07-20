package com.msch.helpapp

import android.os.Bundle
import com.msch.helpapp.dagger.components.ApplicationComponent
import moxy.MvpAppCompatActivity

abstract class BaseActivity: MvpAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.getApplicationComponent()?.inject(this)
    }

    fun getApplicationComponent(): ApplicationComponent? {
        return (application as AndroidApplication).getAppComponent()
    }
}