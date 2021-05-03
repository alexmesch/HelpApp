package com.msch.helpapp.implementations

import android.util.Log
import com.google.android.gms.tasks.Tasks
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.msch.helpapp.data.authentication.FirebaseReg
import com.msch.helpapp.implementations.EmailValidator.isEmailValid

class FirebaseRegImpl: FirebaseReg {
    override fun firebaseReg(email: String, password: String): Boolean {
        val auth = Firebase.auth
        var isSuccessRegister = false
        return if (email.isEmpty() || password.isEmpty() || !isEmailValid(email)) {
            false
        } else {
            val task = auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    isSuccessRegister = if (task.isSuccessful) {
                        Log.d("Firebase", "signInWithEmail: success")
                        auth.currentUser
                        true
                    } else {
                        Log.d("Firebase", "signInWithEmail: failure")
                        false
                    }
                }
            Tasks.await(task)
            isSuccessRegister
        }
    }
}