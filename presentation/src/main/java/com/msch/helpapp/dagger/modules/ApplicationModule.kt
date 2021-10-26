package com.msch.helpapp.dagger.modules

import com.msch.data.network.FirebaseOps
import com.msch.data.repository.DataRepositoryImpl
import com.msch.domain.repository.DataRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule {
    @Singleton
    @Provides
    fun getDataRepository(dr: DataRepositoryImpl): DataRepository = dr

    @Singleton
    @Provides
    fun getFirebaseOps(): FirebaseOps = FirebaseOps()
}