package com.msch.domain

import android.util.Log
import com.google.android.gms.tasks.Tasks
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.msch.domain.EmailValidator.isEmailValid


class FirebaseOps {
    fun firebaseLogin(email: String, password: String): Boolean {
        val auth = Firebase.auth
        var isSuccessLogin = false
        return if (email.isEmpty() || password.isEmpty() || !isEmailValid(email)) {
            false
        } else {
            val task = auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    isSuccessLogin = if (task.isSuccessful) {
                        Log.d("Firebase", "signInWithEmail: success")
                        auth.currentUser
                        true
                    } else {
                        false
                    }
                }
            Tasks.await(task)
            isSuccessLogin
        }
    }

    fun firebaseReg(email: String, password: String): Boolean {
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