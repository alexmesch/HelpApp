package com.msch.helpapp.database

import com.google.firebase.database.DataSnapshot

object FirebaseOperations {
    fun <T: Any> retrieveFirebaseData(dataSnapshot: DataSnapshot, firebaseChild: String, dataType: T): List<T>{
        val fbResponse = dataSnapshot.child(firebaseChild).children
        return fbResponse.mapNotNull{ it.getValue(dataType::class.java) }
    }
}