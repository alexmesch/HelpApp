package com.msch.helpapp.network

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class RealmCategories(
        @PrimaryKey
        var categoryName: String = "",
        var categoryImage: String = ""
) : RealmObject()