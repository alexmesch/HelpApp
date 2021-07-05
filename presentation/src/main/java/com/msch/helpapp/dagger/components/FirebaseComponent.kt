package com.msch.helpapp.dagger.components

import androidx.fragment.app.Fragment
import com.msch.helpapp.dagger.modules.FirebaseModule
import com.msch.helpapp.dagger.modules.FragmentManagerModule
import com.msch.helpapp.views.AuthView
import dagger.Component
import moxy.MvpPresenter
import javax.inject.Singleton

@Singleton
@Component(modules = [FirebaseModule::class, FragmentManagerModule::class])
interface FirebaseComponent {
    fun provideFbOps(): FirebaseModule
    fun getFragmentManager(): FragmentManagerModule

    fun inject(f: Fragment)
}