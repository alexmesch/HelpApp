package com.msch.helpapp.models

data class EventDetails(
        var eventId: String,
        var eventCategory: String,
        var eventMainImage: String,
        var eventSecondaryImages: List<String>,
        var eventFriends: List<String>,
        var eventName: String,
        var eventDate: String,
        var eventOrganizer: String,
        var eventLocation: String,
        var eventContacts: String,
        var eventMail: String,
        var eventDescription: String,
        var eventSite: String
)