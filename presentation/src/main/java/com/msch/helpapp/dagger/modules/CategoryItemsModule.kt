package com.msch.helpapp.dagger.modules

import com.msch.data.datasource.CategoryItemsDS
import dagger.Module
import dagger.Provides
import javax.inject.Inject
import javax.inject.Singleton

@Module
class CategoryItemsModule @Inject constructor() {
    @Provides
    @Singleton
    fun catItemsDS(): CategoryItemsDS {
        return CategoryItemsDS()
    }
}