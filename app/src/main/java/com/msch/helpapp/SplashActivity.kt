package com.msch.helpapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.msch.helpapp.database.RealmCategories
import com.msch.helpapp.database.RealmConfig.realmConfig
import com.msch.helpapp.database.RealmEvents
import io.realm.Realm
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO

class SplashActivity : AppCompatActivity() {
    private lateinit var realm: Realm
    private val realmScope = MainScope()

    override fun onCreate(savedInstanceState: Bundle?) {
        val context = applicationContext
        super.onCreate(savedInstanceState)

        realmScope.launch {
            setContentView(R.layout.splash_screen)
            withContext(IO) {
                Realm.init(applicationContext)
                realm = Realm.getInstance(realmConfig)
                realm.executeTransaction {
                    realm.createOrUpdateAllFromJson(RealmCategories::class.java, context.assets.open("categories"))
                    realm.createOrUpdateAllFromJson(RealmEvents::class.java, context.assets.open("events_information"))
                }
                realm.close()
            }
            startActivity(Intent(applicationContext, MainActivity::class.java))
            finish()
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        realmScope.cancel()
    }
}