package com.msch.domain.repository

import com.msch.domain.model.CategoryItems
import com.msch.domain.model.EventDetails
import com.msch.domain.model.UserProfile
import io.reactivex.Single

interface DataRepository {
    fun getCategoriesDS(): Single<List<CategoryItems>>
    fun getEventsDS(): Single<List<EventDetails>>
    fun getUserDS(userID: String): Single<UserProfile>
}