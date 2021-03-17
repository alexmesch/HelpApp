package com.msch.helpapp.database

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class RealmCategories(
        @PrimaryKey
        var categoryName: String = "",
        var categoryImage: String = ""
) : RealmObject()