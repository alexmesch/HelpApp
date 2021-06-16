package com.msch.helpapp.dagger.components

import com.msch.helpapp.dagger.modules.CategoryItemsModule
import com.msch.helpapp.dagger.modules.EventDetailsModule
import com.msch.helpapp.dagger.modules.UserInfoModule
import dagger.Component

@Component(modules = [EventDetailsModule::class])
interface DataComponent {
    fun getEDComponent(): EventDetailsModule
    fun getCDComponent(): CategoryItemsModule
    fun getUDComponent(): UserInfoModule
}