package com.msch.helpapp.dagger.modules

import com.msch.helpapp.dagger.PerActivity
import com.msch.helpapp.fragments.FragmentsManager
import dagger.Module
import dagger.Provides
import javax.inject.Inject
import javax.inject.Singleton

@Module
class NavigationModule @Inject constructor() {
    @Provides
    @PerActivity
    fun getFragmentsManager(): FragmentsManager {
        return FragmentsManager()
    }
}