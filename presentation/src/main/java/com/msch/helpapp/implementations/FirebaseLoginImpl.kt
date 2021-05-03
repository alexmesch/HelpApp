package com.msch.helpapp.implementations

import android.util.Log
import com.google.android.gms.tasks.Tasks
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.msch.helpapp.data.authentication.FirebaseLogin
import com.msch.helpapp.implementations.EmailValidator.isEmailValid
import java.util.regex.Matcher
import java.util.regex.Pattern

class FirebaseLoginImpl : FirebaseLogin {
    override fun firebaseLogin(email: String, password: String): Boolean {
        val auth = Firebase.auth
        var isSuccessLogin = false
        return if (email.isEmpty() || password.isEmpty() || !isEmailValid(email)) {
            false
        } else {
            val task = auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d("Firebase", "signInWithEmail: success")
                        auth.currentUser
                        isSuccessLogin = true
                    } else {
                        Log.d("Firebase", "signInWithEmail: failure")
                    }
                }
            Tasks.await(task)
            isSuccessLogin
        }
    }
}