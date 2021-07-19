package com.msch.helpapp.dagger.modules

import com.msch.data.network.FirebaseOps
import com.msch.data.repository.DataRepositoryImpl
import com.msch.domain.interactor.GetCategoryItemsUseCase
import com.msch.domain.interactor.GetEventsUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Inject
import javax.inject.Singleton

@Module
class DataModule @Inject constructor() {
    @Singleton
    @Provides
    fun getCatItemsUseCase(): GetCategoryItemsUseCase {
        return GetCategoryItemsUseCase(DataRepositoryImpl())
    }

    @Singleton
    @Provides
    fun getEventDetailsUseCase(): GetEventsUseCase {
        return GetEventsUseCase(DataRepositoryImpl())
    }

    @Singleton
    @Provides
    fun getFirebaseOps(): FirebaseOps {
        return FirebaseOps()
    }
}