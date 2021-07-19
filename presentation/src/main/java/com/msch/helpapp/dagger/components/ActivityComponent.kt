package com.msch.helpapp.dagger.components

import com.msch.helpapp.MainActivity
import com.msch.helpapp.dagger.PerActivity
import com.msch.helpapp.dagger.modules.NavigationModule
import dagger.Component


@PerActivity
@Component(dependencies = [ApplicationComponent::class], modules = [NavigationModule::class])
interface ActivityComponent {
    fun getFragmentManager(): NavigationModule
    fun inject(act: MainActivity)
}