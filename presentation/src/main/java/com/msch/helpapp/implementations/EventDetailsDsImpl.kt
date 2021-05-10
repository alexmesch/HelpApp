package com.msch.helpapp.implementations

import com.google.android.gms.tasks.Tasks
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.msch.helpapp.data.datasource.EventDetailsDS
import com.msch.helpapp.domain.EventDetails

class EventDetailsDsImpl : EventDetailsDS {
    override fun download(): List<EventDetails> {
        val fb = Firebase.database.reference
        val task = fb.child("RealmEvents").get()
        return Tasks.await(task).children.mapNotNull { it.getValue((EventDetails::class.java)) }
    }
}
