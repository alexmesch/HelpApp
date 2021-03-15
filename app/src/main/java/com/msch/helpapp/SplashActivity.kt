package com.msch.helpapp

import android.content.Intent
import android.content.res.AssetManager
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import com.msch.helpapp.models.CategoryItems
import com.msch.helpapp.network.RealmCategories
import com.msch.helpapp.network.RealmEvents
import io.realm.Realm
import io.realm.RealmConfiguration
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import java.io.File
import java.io.FileInputStream
import java.io.InputStream

class SplashActivity : AppCompatActivity() {
    private var realm: Realm? = null
    private val realmScope = MainScope()

    override fun onCreate(savedInstanceState: Bundle?) {
        val context = applicationContext
        super.onCreate(savedInstanceState)
        realmScope.launch {
            setContentView(R.layout.splash_screen)
            withContext(IO) {
                Realm.init(applicationContext)
                val realmConfig = RealmConfiguration.Builder()
                    .name("HelpAppDB.realm")
                    .build()
                realm = Realm.getInstance(realmConfig)
                realm?.executeTransaction {
                    realm?.createOrUpdateAllFromJson(RealmCategories::class.java, context.assets.open("categories"))
                    realm?.createOrUpdateAllFromJson(RealmEvents::class.java, context.assets.open("events_information"))
                }
                realm?.close()
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