package com.msch.helpapp.interactors

import com.msch.helpapp.data.authentication.FirebaseReg

class FirebaseReg(private val firebaseRegImpl: FirebaseReg) {
    operator fun invoke(email: String, password: String) = firebaseRegImpl.firebaseReg(email, password)
}