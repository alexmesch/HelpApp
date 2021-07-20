package com.msch.helpapp.dagger.components

import com.msch.domain.interactor.GetCategoryItemsUseCase
import com.msch.domain.interactor.GetEventsUseCase
import com.msch.domain.interactor.GetUsersUseCase
import com.msch.domain.repository.DataRepository
import com.msch.helpapp.EventDetailsActivity
import com.msch.helpapp.MainActivity
import com.msch.helpapp.dagger.PerActivity
import com.msch.helpapp.dagger.modules.NavigationModule
import dagger.Component

@PerActivity
@Component(dependencies = [ApplicationComponent::class], modules = [NavigationModule::class])
interface ActivityComponent {
    fun inject(activity: MainActivity)
    fun inject(activity: EventDetailsActivity)
}