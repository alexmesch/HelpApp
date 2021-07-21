package com.msch.data.network

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import durdinapps.rxfirebase2.RxFirebaseAuth
import io.reactivex.Single
import javax.inject.Inject


class FirebaseOps @Inject constructor() {
    fun getLoginSingle(email: String, password: String): Single<AuthResult> {
        return RxFirebaseAuth.signInWithEmailAndPassword(Firebase.auth, email, password)
            .toSingle()
    }

    fun getRegisterSingle(email: String, password: String): Single<AuthResult> {
        return RxFirebaseAuth.createUserWithEmailAndPassword(Firebase.auth, email, password)
            .toSingle()
    }
}