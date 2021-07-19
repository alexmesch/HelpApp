package com.msch.helpapp.dagger.components

import com.msch.helpapp.MainActivity
import com.msch.helpapp.dagger.PerFragment
import dagger.Component

@PerFragment
@Component(dependencies = [ActivityComponent::class])
interface FragmentComponent {
    fun inject(act: MainActivity)
}