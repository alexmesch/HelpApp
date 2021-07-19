package com.msch.helpapp.dagger.modules

import com.msch.data.repository.DataRepositoryImpl
import com.msch.domain.interactor.GetEventsUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Inject
import javax.inject.Singleton

@Module
class EventDetailsModule @Inject constructor() {
    @Provides
    @Singleton
    fun getEventDetailsUseCase(): GetEventsUseCase{
        return GetEventsUseCase(DataRepositoryImpl())
    }
}