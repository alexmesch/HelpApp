package com.msch.helpapp.data.datasource

import com.msch.helpapp.domain.EventDetails

interface EventDetailsDS {
    fun download(): List<EventDetails>
}
