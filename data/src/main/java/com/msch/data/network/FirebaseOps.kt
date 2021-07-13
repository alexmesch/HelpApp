package com.msch.data.network

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import durdinapps.rxfirebase2.RxFirebaseAuth
import io.reactivex.Single


class FirebaseOps {
    fun getLoginObservable(email: String, password: String): Single<AuthResult> {
        return RxFirebaseAuth.signInWithEmailAndPassword(Firebase.auth, email, password)
            .toSingle()
    }

    fun getRegisterObservable(email: String, password: String): Single<AuthResult> {
        return RxFirebaseAuth.createUserWithEmailAndPassword(Firebase.auth, email, password)
            .toSingle()
    }
}