package com.msch.helpapp.dagger.components

import com.msch.helpapp.EventDetailsActivity
import com.msch.helpapp.dagger.modules.CategoryItemsModule
import com.msch.helpapp.dagger.modules.EventDetailsModule
import com.msch.helpapp.dagger.modules.FragmentManagerModule
import com.msch.helpapp.dagger.modules.UserInfoModule
import com.msch.helpapp.fragments.FragmentsManager
import com.msch.helpapp.fragments.HelpFragment
import com.msch.helpapp.fragments.NewsFragment
import com.msch.helpapp.fragments.ProfileFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [EventDetailsModule::class, CategoryItemsModule::class, UserInfoModule::class, FragmentManagerModule::class])
interface DataComponent {
    fun getEDComponent(): EventDetailsModule
    fun getCDComponent(): CategoryItemsModule
    fun getUDComponent(): UserInfoModule
    fun getFragmentManager(): FragmentsManager

    fun inject(fragment: HelpFragment)
    fun inject(fragment: NewsFragment)
    fun inject(fragment: ProfileFragment)
    fun inject(act: EventDetailsActivity)
}