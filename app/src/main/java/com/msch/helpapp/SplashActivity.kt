package com.msch.helpapp
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.Task
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.msch.helpapp.database.RealmCategories
import com.msch.helpapp.database.RealmConfig
import com.msch.helpapp.database.RealmConfig.defineRealmConfig
import io.realm.Realm
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import io.realm.mongodb.App
import io.realm.mongodb.AppConfiguration
import io.realm.mongodb.Credentials
import io.realm.mongodb.User
import io.realm.mongodb.sync.SyncConfiguration

class SplashActivity : AppCompatActivity() {
    private lateinit var realm: Realm
    private val realmScope = MainScope()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)

        realmScope.launch {
            withContext(IO) {
                Firebase.database.setPersistenceEnabled(true)
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
