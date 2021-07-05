package com.msch.helpapp.dagger.components

import com.msch.helpapp.dagger.modules.FirebaseModule
import com.msch.helpapp.dagger.modules.FragmentManagerModule
import com.msch.helpapp.fragments.AuthFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [FirebaseModule::class, FragmentManagerModule::class])
interface FirebaseComponent {
    fun provideFbOps(): FirebaseModule
    fun getFragmentManager(): FragmentManagerModule

    fun inject(fragment: AuthFragment)
}