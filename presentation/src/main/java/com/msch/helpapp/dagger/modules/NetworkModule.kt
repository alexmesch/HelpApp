package com.msch.helpapp.dagger.modules

import com.msch.data.network.FirebaseOps
import com.msch.helpapp.dagger.PerActivity
import com.msch.helpapp.dagger.PerFragment
import dagger.Module
import dagger.Provides
import javax.inject.Inject

@Module
class NetworkModule @Inject constructor() {
    @PerFragment
    @Provides
    fun getFirebaseOps(): FirebaseOps {
        return FirebaseOps()
    }
}