package com.msch.helpapp

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.msch.helpapp.adapters.EdFriendsAdapter
import com.msch.helpapp.adapters.EdImagesAdapter
import com.msch.helpapp.objects.JsonParser
import com.msch.helpapp.objects.TimeWorks.calculateEstimatedTime


class EventDetailsActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ac_event_details)

        val eventTitle = findViewById<TextView>(R.id.ed_title)
        val eventSubtitle = findViewById<TextView>(R.id.ed_subtitle)
        val eventDate = findViewById<TextView>(R.id.ed_event_date)
        val eventOrganizer = findViewById<TextView>(R.id.ed_organizer)
        val eventLocation = findViewById<TextView>(R.id.ed_location)
        val eventPhones = findViewById<TextView>(R.id.ed_phones)
        val eventDescription = findViewById<TextView>(R.id.ed_description)

        val fileContent = JsonParser.openFile(assets.open("events_information"))
        val eventInfo = JsonParser.parseEventDetails(fileContent)

        val eId = intent.getStringExtra("ID")
        val eventPosition = eventInfo.indexOfFirst{it.eventId == eId}

        eventTitle.text = eventInfo[eventPosition].eventName
        eventSubtitle.text = eventTitle.text
        eventDate.text = calculateEstimatedTime(eventInfo[eventPosition].eventDate, applicationContext)
        eventOrganizer.text = eventInfo[eventPosition].eventOrganizer
        eventLocation.text = eventInfo[eventPosition].eventLocation
        eventPhones.text = eventInfo[eventPosition].eventContacts
        eventDescription.text = eventInfo[eventPosition].eventDescription

        val imagesRecycler: RecyclerView = findViewById(R.id.ed_images_recycler)
        val imagesAdapter = EdImagesAdapter()
        imagesRecycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false)
        imagesRecycler.adapter = imagesAdapter
        imagesAdapter.submitList(eventInfo[eventPosition].eventSecondaryImages)

        val friendsRecycler: RecyclerView = findViewById(R.id.ed_friends_recycler)
        val friendsAdapter = EdFriendsAdapter()
        friendsRecycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false)
        friendsRecycler.adapter = friendsAdapter
        friendsAdapter.submitList(eventInfo[eventPosition].eventFriends)

    }

    fun finishActivity(view: View) {
        finish()
    }
}