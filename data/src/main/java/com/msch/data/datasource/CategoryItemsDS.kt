package com.msch.data.datasource

import com.google.android.gms.tasks.Tasks
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.msch.data.model.CategoryItems

class CategoryItemsDS{
    fun addCategories(): List<CategoryItems> {
        val fb = Firebase.database.reference
        val task = fb.child("RealmCategories").get()
        return Tasks.await(task).children.mapNotNull { it.getValue(CategoryItems::class.java) }
    }
}