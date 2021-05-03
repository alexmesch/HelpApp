package com.msch.helpapp.interactors

import com.msch.helpapp.data.authentication.FirebaseLogin

class FirebaseLogin(private val firebaseLoginImpl: FirebaseLogin) {
    operator fun invoke(email: String, password: String) = firebaseLoginImpl.firebaseLogin(email, password)
}