package com.msch.helpapp.dagger.components

import com.msch.helpapp.dagger.PerFragment
import com.msch.helpapp.fragments.*
import dagger.Component

@PerFragment
@Component(dependencies = [ActivityComponent::class])
interface FragmentComponent{

    fun inject(f: EventsSearchFragment)
    fun inject(f: HelpFragment)
    fun inject(f: AuthFragment)
    fun inject(f: NewsFragment)
    fun inject(f: ProfileFragment)
}