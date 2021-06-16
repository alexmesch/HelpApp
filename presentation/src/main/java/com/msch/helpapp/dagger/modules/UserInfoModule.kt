package com.msch.helpapp.dagger.modules

import com.msch.data.datasource.UserInfoDS
import dagger.Module
import dagger.Provides
import javax.inject.Inject
import javax.inject.Singleton

@Module
class UserInfoModule @Inject constructor() {
    @Provides
    @Singleton
    fun userInfoDS(): UserInfoDS {
        return UserInfoDS()
    }
}