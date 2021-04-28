package com.msch.helpapp.database

import com.google.firebase.database.DataSnapshot
import com.msch.helpapp.models.UserProfile

object FirebaseOperations {
    fun <T: Any> retrieveFirebaseData(dataSnapshot: DataSnapshot, firebaseChild: String, dataType: T): List<T>{
        val fbResponse = dataSnapshot.child(firebaseChild).children
        return fbResponse.mapNotNull{ it.getValue(dataType::class.java) }
    }

    fun retrieveUserInformation(dataSnapshot: DataSnapshot, firebaseChild: String): UserProfile {
        val fbResponse = dataSnapshot.child("users").child(firebaseChild)
        return fbResponse.getValue(UserProfile::class.java) ?: UserProfile()
    }
}