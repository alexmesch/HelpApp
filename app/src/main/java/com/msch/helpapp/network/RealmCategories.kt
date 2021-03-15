package com.msch.helpapp.network

import io.realm.RealmObject

open class RealmCategories(
        var categoryName: String = "",
        var categoryImage: String = ""
) : RealmObject()