package com.msch.helpapp.dagger.components

import androidx.fragment.app.FragmentManager
import com.msch.data.network.FirebaseOps
import com.msch.domain.repository.DataRepository
import com.msch.helpapp.dagger.modules.ApplicationModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {
    fun getDataRepository(): DataRepository
    fun getNetworkOps(): FirebaseOps
}