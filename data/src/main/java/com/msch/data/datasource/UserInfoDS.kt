package com.msch.data.datasource

import com.google.android.gms.tasks.Tasks
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.msch.data.model.UserProfile

class UserInfoDS{
    fun getUserInfo(userID: String): UserProfile {
        val fb = Firebase.database.reference
        val task = fb.child("users").child(userID).get()

        return Tasks.await(task).getValue(UserProfile::class.java) ?: UserProfile("",ArrayList(), "","","")
    }
}