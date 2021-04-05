package com.msch.helpapp.database

import io.realm.*
import org.bson.types.ObjectId

class MigrateRealmDB: RealmMigration {
    override fun migrate(realm: DynamicRealm, oldVersion: Long, newVersion: Long) {
        var oldVersion = oldVersion
        val schema = realm.schema

        if (oldVersion == 0L) {
            schema.get("RealmEvents")!!
                .addField("testField", String::class.java, FieldAttribute.REQUIRED)
            oldVersion++
        }

        if (oldVersion == 1L) {
            schema.get("RealmCategories")!!
                .addField("testField", Int::class.javaPrimitiveType)
            oldVersion++
        }

        if (oldVersion == 2L) {
            schema.create("RealmTestObject")
                .addField("testField", Boolean::class.java)
            oldVersion++
        }

        if (oldVersion == 3L) {
            schema.get("RealmEvents")!!
                .removeField("testField")
            schema.get("RealmCategories")!!
                .removeField("testField")
            schema.remove("RealmTestObject")
            oldVersion++
        }

        if (oldVersion == 4L) {
            schema.get("RealmEvents")!!
                .removeField("eventSecondaryImages")
                .removeField("eventFriends")
            oldVersion++
        }

        if (oldVersion == 5L) {
            schema.get("RealmEvents")!!
                .addRealmListField("eventSecondaryImages", String::class.java)
                .addRealmListField("eventFriends",String::class.java)
            oldVersion++
        }

        if (oldVersion == 6L) {
            schema.get("RealmCategories")!!
                .addField("_id", ObjectId::class.java)
            schema.get("RealmEvents")!!
                .addField("_id", ObjectId::class.java)
            oldVersion++
        }
    }
}