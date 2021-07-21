package com.msch.helpapp.dagger.components

import com.msch.helpapp.dagger.PerFragment
import com.msch.helpapp.fragments.AuthFragment
import com.msch.helpapp.fragments.HelpFragment
import com.msch.helpapp.fragments.NewsFragment
import com.msch.helpapp.fragments.ProfileFragment
import dagger.Component

@PerFragment
@Component(dependencies = [ActivityComponent::class])
interface FragmentComponent{
    fun inject(f: HelpFragment)
    fun inject(f: AuthFragment)
    fun inject(f: NewsFragment)
    fun inject(f: ProfileFragment)
}