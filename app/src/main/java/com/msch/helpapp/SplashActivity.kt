package com.msch.helpapp

import android.content.Intent
import android.os.Bundle
import android.os.Environment
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.gson.GsonBuilder
import com.msch.helpapp.database.RealmCategories
import com.msch.helpapp.database.RealmConfig.realmConfig
import com.msch.helpapp.database.RealmEvents
import com.msch.helpapp.network.LocalFilesUpdater.updateLocalJson
import io.realm.Realm
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import java.io.File
import java.io.FileInputStream

class SplashActivity : AppCompatActivity() {
    private lateinit var realm: Realm
    private val realmScope = MainScope()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)

        realmScope.launch {
            Realm.init(applicationContext)
            realm = Realm.getInstance(realmConfig)

            withContext(IO) {
                var firebaseRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://helpapp-a8652-default-rtdb.firebaseio.com/")
                var firebaseStorageRef = FirebaseStorage.getInstance().reference
                val firebaseConnectionState = Firebase.database.getReference("/info/connected")

                firebaseConnectionState.addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        val connected = snapshot.getValue(Boolean::class.java) ?: true
                        if (connected) {
                            updateLocalJson(firebaseStorageRef, applicationContext)
                            firebaseRef.get().addOnSuccessListener {

                                val catObj = it.child("RealmCategories").getValue(Any::class.java)
                                val catJson = GsonBuilder().create().toJson(catObj)

                                val eventObj = it.child("RealmEvents").getValue(Any::class.java)
                                val eventJson = GsonBuilder().create().toJson(eventObj)

                                realm.beginTransaction()
                                    realm.createOrUpdateAllFromJson(RealmCategories::class.java,catJson)
                                    realm.createOrUpdateAllFromJson(RealmEvents::class.java, eventJson)
                                realm.commitTransaction()
                            }.addOnFailureListener {
                                Log.e("firebase", "Error reading remote file")
                            }
                        } else {
                            realm.beginTransaction()
                                realm.createOrUpdateAllFromJson(RealmCategories::class.java,FileInputStream(File(Environment.getDataDirectory(), "JSON/categories.json")))
                                realm.createOrUpdateAllFromJson(RealmEvents::class.java, FileInputStream(File(Environment.getDataDirectory(), "JSON/event_info.json")))
                            realm.commitTransaction()
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {
                        Log.w("firebaseWarning", "Listener was cancelled")
                    }
                })
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