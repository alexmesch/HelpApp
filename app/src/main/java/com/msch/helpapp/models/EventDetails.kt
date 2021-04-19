package com.msch.helpapp.models

data class EventDetails(
        var eventId: String = "Ошибка",
        var eventCategory: String = "Ошибка",
        var eventMainImage: String = "Ошибка",
        var eventSecondaryImages: List<String> = ArrayList(),
        var eventFriends: List<String> = ArrayList(),
        var eventName: String = "Ошибка",
        var eventDate: String = "Ошибка",
        var eventOrganizer: String = "Ошибка",
        var eventLocation: String = "Ошибка",
        var eventContacts: String = "Ошибка",
        var eventMail: String = "Ошибка",
        var eventDescription: String = "Ошибка",
        var eventSite: String = "Ошибка"
)