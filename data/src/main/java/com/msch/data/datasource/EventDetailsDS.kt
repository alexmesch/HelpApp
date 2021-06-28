package com.msch.data.datasource

import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.msch.domain.model.EventDetails
import durdinapps.rxfirebase2.DataSnapshotMapper
import durdinapps.rxfirebase2.RxFirebaseDatabase
import io.reactivex.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class EventDetailsDS {
    private val fbRef = Firebase.database.reference
    private val fbPath = "RealmEvents"

    fun getEdObservable(): Single<List<EventDetails>> {
        return RxFirebaseDatabase.observeSingleValueEvent(
            fbRef.child(fbPath),
            DataSnapshotMapper.listOf(EventDetails::class.java)
        )
            .toSingle()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}
