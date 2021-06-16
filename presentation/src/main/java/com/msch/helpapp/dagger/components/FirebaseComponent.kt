package com.msch.helpapp.dagger.components

import com.msch.helpapp.dagger.modules.FirebaseModule
import dagger.Component

@Component(modules = [FirebaseModule::class])
interface FirebaseComponent {
    fun provideFbOps(): FirebaseModule
}