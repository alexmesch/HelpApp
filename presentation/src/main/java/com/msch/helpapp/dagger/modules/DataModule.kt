package com.msch.helpapp.dagger.modules

import com.msch.data.repository.DataRepositoryImpl
import com.msch.domain.interactor.GetCategoryItemsUseCase
import com.msch.domain.interactor.GetEventsUseCase
import com.msch.domain.interactor.GetUsersUseCase
import com.msch.domain.repository.DataRepository
import com.msch.helpapp.dagger.PerFragment
import dagger.Module
import dagger.Provides
import javax.inject.Inject
import javax.inject.Singleton

@Module
class DataModule @Inject constructor() {
    @Singleton
    @Provides
    fun getEventDetailsUseCase(): GetEventsUseCase {
        return GetEventsUseCase(DataRepositoryImpl())
    }
}