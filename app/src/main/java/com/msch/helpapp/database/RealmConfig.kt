package com.msch.helpapp.database

import io.realm.RealmConfiguration

object RealmConfig {
    val realmConfig: RealmConfiguration = RealmConfiguration.Builder()
        .schemaVersion(4)
        .name("HelpAppDB.realm")
        .migration(MigrateRealmDB())
        .build()
}