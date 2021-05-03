package com.msch.helpapp.data.authentication

interface FirebaseReg {
    fun firebaseReg(email: String, password: String): Boolean
}