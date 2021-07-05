package com.msch.helpapp.dagger.components

import com.msch.helpapp.MainActivity
import com.msch.helpapp.dagger.modules.FragmentManagerModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [FragmentManagerModule::class])
interface FragmentManagerComponent {
    fun getFragmentManager(): FragmentManagerModule

    fun inject(act: MainActivity)

}