package com.msch.helpapp.dagger.modules

import com.msch.data.network.FirebaseOps
import dagger.Module
import dagger.Provides
import javax.inject.Inject
import javax.inject.Singleton

@Module
class FirebaseModule @Inject constructor() {
    @Provides
    @Singleton
    fun getFirebaseOps(): FirebaseOps {
        return FirebaseOps()
    }
}