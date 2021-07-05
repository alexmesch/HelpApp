package com.msch.helpapp.dagger.components

import androidx.fragment.app.Fragment
import com.msch.helpapp.dagger.modules.FragmentManagerModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [FragmentManagerModule::class])
interface FragmentManagerComponent {
    fun getFragmentManager(): FragmentManagerModule

    fun inject(fragment: Fragment)
}