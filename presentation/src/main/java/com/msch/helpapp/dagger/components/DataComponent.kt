package com.msch.helpapp.dagger.components

import androidx.fragment.app.Fragment
import com.msch.helpapp.dagger.modules.CategoryItemsModule
import com.msch.helpapp.dagger.modules.EventDetailsModule
import com.msch.helpapp.dagger.modules.UserInfoModule
import com.msch.helpapp.presenters.HelpPresenter
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [EventDetailsModule::class, CategoryItemsModule::class, UserInfoModule::class])
interface DataComponent {
    fun getEDComponent(): EventDetailsModule
    fun getCDComponent(): CategoryItemsModule
    fun getUDComponent(): UserInfoModule

    fun inject(presenter: HelpPresenter)
}