package com.msch.data.datasource

import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.msch.domain.model.UserProfile
import durdinapps.rxfirebase2.DataSnapshotMapper
import durdinapps.rxfirebase2.RxFirebaseDatabase
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class UserInfoDS{
    fun getUserObservable(userID: String): Single<UserProfile> {
        val fbRef = Firebase.database.reference
        val fbPath = "users"

        return RxFirebaseDatabase.observeSingleValueEvent(
            fbRef.child(fbPath).child(userID), DataSnapshotMapper.of(UserProfile::class.java))
            .toSingle()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}