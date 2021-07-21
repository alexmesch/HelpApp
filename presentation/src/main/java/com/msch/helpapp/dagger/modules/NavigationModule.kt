package com.msch.helpapp.dagger.modules

import androidx.fragment.app.FragmentManager
import com.msch.helpapp.dagger.PerActivity
import com.msch.helpapp.fragments.FragmentsManager
import dagger.Module
import dagger.Provides

@Module
class NavigationModule{
    @PerActivity
    @Provides
    fun getFragmentsManager(fm: FragmentManager): FragmentsManager = FragmentsManager(fm)
}