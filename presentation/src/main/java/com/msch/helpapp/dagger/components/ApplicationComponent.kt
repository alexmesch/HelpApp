package com.msch.helpapp.dagger.components

import com.msch.helpapp.BaseActivity
import com.msch.helpapp.EventDetailsActivity
import com.msch.helpapp.dagger.modules.DataModule
import com.msch.helpapp.fragments.HelpFragment
import com.msch.helpapp.fragments.NewsFragment
import com.msch.helpapp.fragments.ProfileFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DataModule::class])
interface ApplicationComponent {
    fun provideData(): DataModule

    fun inject(activity: BaseActivity)
    fun inject(fragment: HelpFragment)
    fun inject(fragment: NewsFragment)
    fun inject(fragment: ProfileFragment)
    fun inject(act: EventDetailsActivity)
}