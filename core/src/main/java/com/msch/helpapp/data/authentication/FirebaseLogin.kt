package com.msch.helpapp.data.authentication

interface FirebaseLogin {
    fun firebaseLogin(email: String, password: String): Boolean
}