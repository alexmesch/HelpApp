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

    fun retrieveEventsData(dataSnapshot: DataSnapshot, firebaseChild: String): List<EventDetails>{
        val errorList: List<EventDetails> = listOf(EventDetails())

        val fbResponse = dataSnapshot.child(firebaseChild).children
        return fbResponse.map { it.getValue(EventDetails::class.java) ?: errorList } as List<EventDetails>

        /*dataSnapshot.child(firebaseChild).children.forEach {
            item = it.getValue(EventDetails::class.java)!!
            /*item = EventDetails(
                it.child("eventId").value.toString(),
                it.child("eventCategory").value.toString(),
                it.child("eventMainImage").value.toString(),
                it.child("eventSecondaryImages").getValue(listFormat)?: errorImageList,
                it.child("eventFriends").getValue(listFormat)?: errorImageList,
                it.child("eventName").value.toString(),
                it.child("eventDate").value.toString(),
                it.child("eventOrganizer").value.toString(),
                it.child("eventLocation").value.toString(),
                it.child("eventContacts").value.toString(),
                it.child("eventMail").value.toString(),
                it.child("eventDescription").value.toString(),
                it.child("eventSite").value.toString()
            )*/
            //result.add(item)
        }*/
    }
}