package com.msch.helpapp.dagger.components

import com.msch.data.repository.DataRepositoryImpl
import com.msch.domain.interactor.GetCategoryItemsUseCase
import com.msch.domain.interactor.GetEventsUseCase
import com.msch.domain.interactor.GetUsersUseCase
import com.msch.domain.repository.DataRepository
import com.msch.helpapp.BaseActivity
import com.msch.helpapp.EventDetailsActivity
import com.msch.helpapp.MainActivity
import com.msch.helpapp.dagger.modules.DataModule
import com.msch.helpapp.fragments.AuthFragment
import com.msch.helpapp.fragments.HelpFragment
import com.msch.helpapp.fragments.NewsFragment
import com.msch.helpapp.fragments.ProfileFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DataModule::class])
interface ApplicationComponent {
    fun getEventsUseCase(): GetEventsUseCase

    fun inject(baseActivity: BaseActivity)
    fun inject(mainActivity: MainActivity)
    fun inject(edAct: EventDetailsActivity)
}