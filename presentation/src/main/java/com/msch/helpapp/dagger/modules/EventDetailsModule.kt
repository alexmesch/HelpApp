package com.msch.helpapp.dagger.modules

import com.msch.data.datasource.EventDetailsDS
import dagger.Module
import dagger.Provides
import javax.inject.Inject
import javax.inject.Singleton

@Module
class EventDetailsModule @Inject constructor() {
    @Provides
    @Singleton
    fun eventDetailsDS(): EventDetailsDS {
        return EventDetailsDS()
    }
}