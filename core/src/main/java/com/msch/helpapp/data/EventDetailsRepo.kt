package com.msch.helpapp.data

import com.msch.helpapp.data.datasource.EventDetailsDS

class EventDetailsRepo(private val dataSource: EventDetailsDS) {
    fun addEvents() = dataSource.download()
}