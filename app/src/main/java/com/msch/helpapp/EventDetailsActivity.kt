package com.msch.helpapp

import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.widget.FrameLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.reflect.TypeToken
import com.msch.helpapp.adapters.EdFriendsAdapter
import com.msch.helpapp.adapters.EdImagesAdapter
import com.msch.helpapp.concurrency.FileCoroutine.fileWorksThread
import com.msch.helpapp.concurrency.FileCoroutine.logThread
import com.msch.helpapp.database.RealmConfig.realmConfig
import com.msch.helpapp.database.RealmEvents
import com.msch.helpapp.models.EventDetails
import com.msch.helpapp.objects.JsonParser.parseJson
import com.msch.helpapp.objects.TimeWorks.calculateEstimatedTime
import io.realm.Realm
import io.realm.kotlin.where
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO


class EventDetailsActivity : AppCompatActivity() {
    private val listType = object : TypeToken<List<EventDetails>>() {}.type
    private var eventInfo: List<EventDetails> = ArrayList()
    private val EVENTS_INFORMATION = "events_information"
    private val lifecycleScope = MainScope()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            withContext(IO){
                val realm = Realm.getInstance(realmConfig)
                val realmData = realm.where(RealmEvents::class.java).findAll()
                eventInfo = parseJson(realmData.asJSON().toString(), listType).filterIsInstance<EventDetails>()
                //eventInfo = fileWorksThread(applicationContext, listType, EVENTS_INFORMATION).filterIsInstance<EventDetails>()
                realm.close()
            }
            setContentView(R.layout.ac_event_details)
            val eventTitle = findViewById<TextView>(R.id.ed_title)
            val eventSubtitle = findViewById<TextView>(R.id.ed_subtitle)
            val eventDate = findViewById<TextView>(R.id.ed_event_date)
            val eventOrganizer = findViewById<TextView>(R.id.ed_organizer)
            val eventLocation = findViewById<TextView>(R.id.ed_location)
            val eventPhones = findViewById<TextView>(R.id.ed_phones)
            val eventDescription = findViewById<TextView>(R.id.ed_description)
            val eId = intent.getStringExtra("ID")
            val eventPosition = eventInfo.indexOfFirst { it.eventId == eId }

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

            val friendsRecycler: RecyclerView = findViewById(R.id.ed_friends_recycler)
            val friendsAdapter = EdFriendsAdapter()
            friendsRecycler.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)
            friendsRecycler.adapter = friendsAdapter
            friendsAdapter.submitList(eventInfo[eventPosition].eventFriends)

            findViewById<FrameLayout>(R.id.ed_loadingScreen).visibility = GONE
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        lifecycleScope.cancel()
    }

    fun finishActivity(view: View) {
        finish()
    }
}