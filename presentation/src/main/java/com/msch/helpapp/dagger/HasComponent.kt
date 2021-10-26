package com.msch.helpapp.dagger

import com.msch.helpapp.dagger.components.ActivityComponent

interface HasComponent<C> {
    fun getComponent(): ActivityComponent?
}