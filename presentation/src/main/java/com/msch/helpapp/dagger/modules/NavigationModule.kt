package com.msch.helpapp.dagger.modules

import androidx.fragment.app.FragmentManager
import com.msch.helpapp.dagger.PerActivity
import com.msch.helpapp.fragments.Router
import dagger.Module
import dagger.Provides

@Module
class NavigationModule (var fm: FragmentManager){
    @PerActivity
    @Provides
    fun getFragmentsManager(): Router = Router(fm)
}