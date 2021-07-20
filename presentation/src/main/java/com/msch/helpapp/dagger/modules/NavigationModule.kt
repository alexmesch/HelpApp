package com.msch.helpapp.dagger.modules

import com.msch.helpapp.dagger.PerActivity
import com.msch.helpapp.fragments.FragmentsManager
import dagger.Module
import dagger.Provides
import javax.inject.Inject

@Module
class NavigationModule @Inject constructor() {
    @PerActivity
    @Provides
    fun getFragmentsManager(): FragmentsManager {
        return FragmentsManager()
    }
}