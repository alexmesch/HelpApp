package com.msch.helpapp.database

import android.os.Build
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.Required
import org.bson.types.ObjectId

open class RealmCategories(
        var _id: ObjectId = ObjectId(),
        var _partitionValue: String = "HelpApp",

        var categoryName: String = "",
        var categoryImage: String = ""
) : RealmObject()