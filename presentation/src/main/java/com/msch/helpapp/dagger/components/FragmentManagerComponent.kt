package com.msch.helpapp.dagger.components

import com.msch.helpapp.dagger.modules.FragmentManagerModule
import dagger.Component

@Component
interface FragmentManagerComponent {
    fun getFragmentManager(): FragmentManagerModule
}