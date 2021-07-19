package com.msch.helpapp.dagger.modules

import android.content.Context
import com.msch.helpapp.AndroidApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(application: AndroidApplication) {
    private val application: AndroidApplication = application

    @Provides
    @Singleton
    fun provideApplicationContext(): Context {
        return application
    }
}