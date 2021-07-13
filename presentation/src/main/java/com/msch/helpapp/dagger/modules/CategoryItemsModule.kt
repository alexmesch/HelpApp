package com.msch.helpapp.dagger.modules

import com.msch.data.repository.DataRepositoryImpl
import com.msch.domain.interactor.GetCategoryItemsUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Inject
import javax.inject.Singleton

@Module
class CategoryItemsModule @Inject constructor() {
    @Provides
    @Singleton
    fun catItemsDS(): GetCategoryItemsUseCase {
        return GetCategoryItemsUseCase(DataRepositoryImpl())
    }
}