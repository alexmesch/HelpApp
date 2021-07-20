package com.msch.helpapp.dagger.components

import com.msch.domain.interactor.GetCategoryItemsUseCase
import com.msch.domain.interactor.GetEventsUseCase
import com.msch.domain.interactor.GetUsersUseCase
import com.msch.helpapp.dagger.PerFragment
import com.msch.helpapp.dagger.modules.InteractorModule
import com.msch.helpapp.dagger.modules.NetworkModule
import com.msch.helpapp.fragments.AuthFragment
import com.msch.helpapp.fragments.HelpFragment
import com.msch.helpapp.fragments.NewsFragment
import com.msch.helpapp.fragments.ProfileFragment
import dagger.Component

@PerFragment
@Component(dependencies = [ActivityComponent::class], modules = [InteractorModule::class, NetworkModule::class])
interface FragmentComponent: ActivityComponent {
    fun getInteractorModule(): InteractorModule

    fun inject(f: HelpFragment)
    fun inject(f: AuthFragment)
    fun inject(f: NewsFragment)
    fun inject(f: ProfileFragment)
}