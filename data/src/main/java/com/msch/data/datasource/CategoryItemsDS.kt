package com.msch.data.datasource

import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.msch.domain.model.CategoryItems
import durdinapps.rxfirebase2.DataSnapshotMapper
import durdinapps.rxfirebase2.RxFirebaseDatabase
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CategoryItemsDS {
    private val fbRef = Firebase.database.reference
    private val fbPath = "RealmCategories"

    fun getCatObservable(): Single<List<CategoryItems>> {
        return RxFirebaseDatabase.observeSingleValueEvent(
            fbRef.child(fbPath),
            DataSnapshotMapper.listOf(CategoryItems::class.java)
        )
            .toSingle()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}