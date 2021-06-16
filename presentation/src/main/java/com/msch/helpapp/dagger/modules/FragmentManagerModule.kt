package com.msch.helpapp.dagger.modules

import com.msch.helpapp.fragments.FragmentsManager
import dagger.Module
import dagger.Provides
import javax.inject.Inject
import javax.inject.Singleton

@Module
class FragmentManagerModule @Inject constructor() {
    @Provides
    @Singleton
    fun fm(): FragmentsManager {
        return FragmentsManager()
    }
}