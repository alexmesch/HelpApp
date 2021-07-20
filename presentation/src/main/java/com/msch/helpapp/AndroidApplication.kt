package com.msch.helpapp

import android.app.Application
import androidx.multidex.MultiDex
import com.msch.helpapp.dagger.components.ApplicationComponent
import com.msch.helpapp.dagger.components.DaggerApplicationComponent
import com.msch.helpapp.dagger.modules.DataModule

class AndroidApplication: Application() {
    private var appComponent: ApplicationComponent? = null

    override fun onCreate() {
        MultiDex.install(this)
        super.onCreate()
        this.initializeInjector()
    }

    private fun initializeInjector() {
        this.appComponent =
            DaggerApplicationComponent
                .builder()
                .dataModule(DataModule())
                .build()
    }

    fun getAppComponent(): ApplicationComponent? {
        return this.appComponent
    }
}