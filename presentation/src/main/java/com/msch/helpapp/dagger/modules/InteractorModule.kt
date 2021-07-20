package com.msch.helpapp.dagger.modules

import com.msch.data.repository.DataRepositoryImpl
import com.msch.domain.interactor.GetCategoryItemsUseCase
import com.msch.domain.interactor.GetEventsUseCase
import com.msch.domain.interactor.GetUsersUseCase
import com.msch.helpapp.dagger.PerFragment
import dagger.Module
import dagger.Provides
import javax.inject.Inject

@Module
class InteractorModule @Inject constructor() {
    @PerFragment
    @Provides
    fun getCatItemsUseCase(): GetCategoryItemsUseCase = GetCategoryItemsUseCase(DataRepositoryImpl())

    @PerFragment
    @Provides
    fun getEventDetailsUseCase(): GetEventsUseCase = GetEventsUseCase(DataRepositoryImpl())

    @PerFragment
    @Provides
    fun getUsersUseCase(): GetUsersUseCase = GetUsersUseCase(DataRepositoryImpl())
}