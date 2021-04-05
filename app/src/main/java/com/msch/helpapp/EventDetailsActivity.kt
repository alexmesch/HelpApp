package com.msch.helpapp

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.GONE
import android.widget.FrameLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.gson.reflect.TypeToken
import com.msch.helpapp.adapters.EdFriendsAdapter
import com.msch.helpapp.adapters.EdImagesAdapter
import com.msch.helpapp.database.FirebaseOperations
import com.msch.helpapp.database.RealmConfig.defineRealmConfig
import com.msch.helpapp.database.RealmEvents
import com.msch.helpapp.models.EventDetails
import com.msch.helpapp.objects.JsonParser.parseJson
import com.msch.helpapp.objects.TimeWorks.calculateEstimatedTime
import io.realm.Realm
import io.realm.kotlin.where
import kotlinx.android.synthetic.main.fragment_help_screen.view.*
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO


class EventDetailsActivity : AppCompatActivity() {
    private var eventInfo: List<EventDetails> = ArrayList()
    private val lifecycleScope = MainScope()
    private var eventPosition = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ac_event_details)

        lifecycleScope.launch {
            val dbRef = Firebase.database.reference
            dbRef.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    eventInfo = FirebaseOperations.retrieveEventsData(dataSnapshot, "RealmEvents")
                    val eId = intent.getStringExtra("ID")
                    eventPosition = eventInfo.indexOfFirst { it.eventId == eId }
                    val eventTitle = findViewById<TextView>(R.id.ed_title)
                    val eventSubtitle = findViewById<TextView>(R.id.ed_subtitle)
                    val eventDate = findViewById<TextView>(R.id.ed_event_date)
                    val eventOrganizer = findViewById<TextView>(R.id.ed_organizer)
                    val eventLocation = findViewById<TextView>(R.id.ed_location)
                    val eventPhones = findViewById<TextView>(R.id.ed_phones)
                    val eventDescription = findViewById<TextView>(R.id.ed_description)

                    eventTitle.text = eventInfo[eventPosition].eventName
                    eventSubtitle.text = eventTitle.text
                    eventDate.text = calculateEstimatedTime(eventInfo[eventPosition].eventDate, applicationContext)
                    eventOrganizer.text = eventInfo[eventPosition].eventOrganizer
                    eventLocation.text = eventInfo[eventPosition].eventLocation
                    eventPhones.text = eventInfo[eventPosition].eventContacts
                    eventDescription.text = eventInfo[eventPosition].eventDescription

                    val imagesRecycler: RecyclerView = findViewById(R.id.ed_images_recycler)
                    val imagesAdapter = EdImagesAdapter()
                    imagesRecycler.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)
                    imagesRecycler.adapter = imagesAdapter
                    imagesAdapter.submitList(eventInfo[eventPosition].eventSecondaryImages)
                    Log.i("pics:", eventInfo[eventPosition].eventSecondaryImages.toString())

                    val friendsRecycler: RecyclerView = findViewById(R.id.ed_friends_recycler)
                    val friendsAdapter = EdFriendsAdapter()
                    friendsRecycler.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)
                    friendsRecycler.adapter = friendsAdapter
                    friendsAdapter.submitList(eventInfo[eventPosition].eventFriends)
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    Log.w("Firebase", "Load: cancelled", databaseError.toException())
                }
            })
        }
    }

    override fun onResume() {
        super.onResume()
        findViewById<FrameLayout>(R.id.ed_loadingScreen).visibility = GONE
    }

    override fun onDestroy() {
        super.onDestroy()
        lifecycleScope.cancel()
    }

    fun finishActivity(view: View) {
        finish()
    }
}