package com.msch.helpapp.database

import com.google.firebase.database.DataSnapshot
import com.msch.helpapp.models.CategoryItems
import com.msch.helpapp.models.EventDetails

object FirebaseOperations {
    fun retrieveCategoriesData(dataSnapshot: DataSnapshot, firebaseChild: String): ArrayList<CategoryItems>{
        val result: ArrayList<CategoryItems> = ArrayList()
        lateinit var item: CategoryItems

        for (ds in dataSnapshot.child(firebaseChild).children) {
            item = CategoryItems(ds.child("categoryName").value.toString(), ds.child("categoryImage").value.toString())
            result.add(item)
        }

        return result
    }

    fun retrieveEventsData(dataSnapshot: DataSnapshot, firebaseChild: String): ArrayList<EventDetails>{
        var result: ArrayList<EventDetails> = ArrayList()
        lateinit var item: EventDetails

        for (ds in dataSnapshot.child(firebaseChild).children) {
            item = EventDetails(
                ds.child("eventId").value.toString(),
                ds.child("eventCategory").value.toString(),
                ds.child("eventMainImage").value.toString(),
                listOf(ds.child("eventSecondaryImages").value.toString()),
                listOf(ds.child("eventFriends").value.toString()),
                ds.child("eventName").value.toString(),
                ds.child("eventDate").value.toString(),
                ds.child("eventOrganizer").value.toString(),
                ds.child("eventLocation").value.toString(),
                ds.child("eventContacts").value.toString(),
                ds.child("eventMail").value.toString(),
                ds.child("eventDescription").value.toString(),
                ds.child("eventSite").value.toString()
            )
            result.add(item)
        }
        return result
    }
}