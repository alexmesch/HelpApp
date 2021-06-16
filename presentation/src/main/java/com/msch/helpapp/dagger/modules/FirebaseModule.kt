package com.msch.helpapp.dagger.modules

import com.msch.domain.FirebaseOps
import dagger.Module
import dagger.Provides
import javax.inject.Inject
import javax.inject.Singleton

@Module
class FirebaseModule @Inject constructor() {
    @Provides
    @Singleton
    fun fbOps(): FirebaseOps {
        return FirebaseOps()
    }
}