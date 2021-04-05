package com.msch.helpapp.database

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.Required
import org.bson.types.ObjectId

open class RealmEvents(
    @PrimaryKey
    var _id: ObjectId = ObjectId(),
    @Required
    var _partitionValue: String = "HelpApp",

    var eventId: String = "",
    var eventCategory: String = "",
    var eventMainImage: String = "",
    var eventSecondaryImages: RealmList<String> = RealmList(),
    var eventFriends: RealmList<String> = RealmList(),
    var eventName: String = "",
    var eventDate: String = "",
    var eventOrganizer: String = "",
    var eventLocation: String = "",
    var eventContacts: String = "",
    var eventMail: String = "",
    var eventDescription: String = "",
    var eventSite: String = ""
): RealmObject()