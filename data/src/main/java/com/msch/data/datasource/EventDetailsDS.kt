package com.msch.data.datasource

import android.util.Log
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.msch.data.model.EventDetails
import durdinapps.rxfirebase2.DataSnapshotMapper
import durdinapps.rxfirebase2.RxFirebaseDatabase
import io.reactivex.*
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class EventDetailsDS {
    private val fbRef = Firebase.database.reference
    private val fbPath = "RealmEvents"

    fun download(): List<EventDetails> {
        val disposables = CompositeDisposable()
        var data: List<EventDetails> = ArrayList()

        val subscriber = object : SingleObserver<List<EventDetails>> {
            override fun onSubscribe(d: Disposable) {
                disposables.add(d)
            }

            override fun onSuccess(t: List<EventDetails>) {
                data = t
                Log.d("onSuccess", data.toString())
                disposables.clear()
            }

            override fun onError(e: Throwable) {
                Log.e("eventDetailsDS:", "subscriptionFail")
                e.stackTrace
            }
        }

        RxFirebaseDatabase.observeSingleValueEvent(
            fbRef.child(fbPath),
            DataSnapshotMapper.listOf(EventDetails::class.java)
        )
            .toSingle()
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe(subscriber)

        Log.d("download", data.toString())
        return data
    }
}
