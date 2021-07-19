package com.msch.helpapp.dagger.modules

import com.msch.data.repository.DataRepositoryImpl
import com.msch.domain.interactor.GetUsersUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Inject
import javax.inject.Singleton

@Module
class UserInfoModule @Inject constructor() {
    @Provides
    @Singleton
    fun getUserInfoUseCase(): GetUsersUseCase {
        return GetUsersUseCase(DataRepositoryImpl())
    }
}