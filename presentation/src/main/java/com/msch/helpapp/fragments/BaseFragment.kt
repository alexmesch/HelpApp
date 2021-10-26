package com.msch.helpapp.fragments

import com.msch.helpapp.dagger.HasComponent
import moxy.MvpAppCompatFragment

abstract class BaseFragment: MvpAppCompatFragment() {
    protected open fun <C> getActivityComponent(componentType: Class<C>): C? {
        return componentType.cast((activity as HasComponent<C>?)!!.getComponent())
    }
}