package com.msch.helpapp.network

import android.content.Context
import android.util.Log
import com.google.firebase.storage.StorageReference
import java.io.File

object LocalFilesUpdater {
    fun updateLocalJson(storageRef: StorageReference, context: Context) {
        val catRef = storageRef.child("categories.json")
        val eventInfoRef = storageRef.child("events_information.json")
        val localFilePath = File(context.filesDir, "JSON")
        if (!localFilePath.exists()) {
            localFilePath.mkdir()
        }

        val catPath = File(localFilePath,"categories.json")
        val eventInfoPath = File(localFilePath, "event_info.json")

        catRef.getFile(catPath).addOnSuccessListener {
            Log.i("cat file download:","success!")
        }.addOnFailureListener {
            Log.e("cat file download:", "failure")
        }

        eventInfoRef.getFile(eventInfoPath).addOnSuccessListener {
            Log.i("events file download:","success!")
        }.addOnFailureListener {
            Log.e("events file download:", "failure")
        }
    }
}