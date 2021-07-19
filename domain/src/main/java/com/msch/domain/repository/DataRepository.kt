package com.msch.domain.repository

import com.msch.domain.model.CategoryItems
import com.msch.domain.model.EventDetails
import com.msch.domain.model.UserProfile
import io.reactivex.Single

interface DataRepository {
    fun getCategoriesSingle(): Single<List<CategoryItems>>
    fun getEventsSingle(): Single<List<EventDetails>>
    fun getUserSingle(userID: String): Single<UserProfile>
}