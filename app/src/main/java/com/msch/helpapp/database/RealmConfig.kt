package com.msch.helpapp.database

import android.util.Log
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.mongodb.App
import io.realm.mongodb.AppConfiguration
import io.realm.mongodb.Credentials
import io.realm.mongodb.User
import io.realm.mongodb.sync.SyncConfiguration

object RealmConfig {
    private val appID: String = "helpapp-cdkmg"
    private lateinit var config: RealmConfiguration
    private val credentials: Credentials = Credentials.anonymous()

    fun defineRealmConfig(): RealmConfiguration {
        val app = App(AppConfiguration.Builder(appID).build())
        val login = app.login(credentials)
            if (login.isLoggedIn) {
                Log.v("Realm login:", "Success!")
                val user: User? = app.currentUser()
                val partitionValue = "HelpApp"
                config = SyncConfiguration.Builder(user,partitionValue)
                    .allowQueriesOnUiThread(true)
                    .allowWritesOnUiThread(true)
                    .build()
            }
            else {
                config = RealmConfiguration.Builder()
                    .assetFile("s_HelpApp.realm")
                    .build()
            }
        return config
    }

    /*val realmConfig: RealmConfiguration = RealmConfiguration.Builder()
        .schemaVersion(6)
        .name("HelpAppDB.realm")
        .migration(MigrateRealmDB())
        .build()*/
}