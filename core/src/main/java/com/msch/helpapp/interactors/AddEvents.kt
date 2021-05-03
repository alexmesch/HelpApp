package com.msch.helpapp.interactors

import com.msch.helpapp.data.EventDetailsRepo

class AddEvents(private val eventDetails: EventDetailsRepo) {
    operator fun invoke() = eventDetails.addEvents()
}