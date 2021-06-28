package com.msch.domain.model

data class EventDetails(
        val eventId: String? = null,
        val eventCategory: String? = null,
        val eventMainImage: String? = null,
        val eventSecondaryImages: List<String>? = null,
        val eventFriends: List<String>? = null,
        val eventName: String? = null,
        val eventDate: String? = null,
        val eventOrganizer: String? = null,
        val eventLocation: String? = null,
        val eventContacts: String? = null,
        val eventMail: String? = null,
        val eventDescription: String? = null,
        val eventSite: String? = null
)