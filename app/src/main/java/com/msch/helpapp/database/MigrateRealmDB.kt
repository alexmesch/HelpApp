package com.msch.helpapp.database

import io.realm.DynamicRealm
import io.realm.FieldAttribute
import io.realm.RealmMigration

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
    }
}