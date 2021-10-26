package com.msch.data.repository

import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.msch.domain.model.CategoryItems
import com.msch.domain.model.EventDetails
import com.msch.domain.model.UserProfile
import com.msch.domain.repository.DataRepository
import durdinapps.rxfirebase2.DataSnapshotMapper
import durdinapps.rxfirebase2.RxFirebaseDatabase
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class DataRepositoryImpl @Inject constructor(): DataRepository  {
    private val fbRef = Firebase.database.reference
    private val fbCatPath = "RealmCategories"
    private val fbEventPath = "RealmEvents"
    private val fbUsersPath = "users"

    override fun getCategoriesSingle(): Single<List<CategoryItems>> {
        return RxFirebaseDatabase.observeSingleValueEvent(
            fbRef.child(fbCatPath),
            DataSnapshotMapper.listOf(CategoryItems::class.java)
        )
            .defaultIfEmpty(ArrayList())
            .toSingle()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getEventsSingle(): Single<List<EventDetails>> {
        return RxFirebaseDatabase.observeSingleValueEvent(
            fbRef.child(fbEventPath),
            DataSnapshotMapper.listOf(EventDetails::class.java)
        )
            .defaultIfEmpty(ArrayList())
            .toSingle()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getUserSingle(userID: String): Single<UserProfile> {
        return RxFirebaseDatabase.observeSingleValueEvent(
            fbRef.child(fbUsersPath).child(userID),
            DataSnapshotMapper.of(UserProfile::class.java)
        )
            .defaultIfEmpty(UserProfile())
            .toSingle()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}