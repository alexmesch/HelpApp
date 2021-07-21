package com.msch.helpapp.dagger.components

import com.msch.domain.repository.DataRepository
import com.msch.helpapp.EventDetailsActivity
import com.msch.helpapp.MainActivity
import com.msch.helpapp.dagger.PerActivity
import com.msch.helpapp.dagger.modules.NavigationModule
import com.msch.helpapp.fragments.Router
import dagger.Component

@PerActivity
@Component(dependencies = [ApplicationComponent::class], modules = [NavigationModule::class])
interface ActivityComponent {
    fun getDataRepository(): DataRepository
    fun provideFragmentManager(): Router

    fun inject(activity: MainActivity)
    fun inject(activity: EventDetailsActivity)
}