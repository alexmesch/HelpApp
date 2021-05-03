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
import com.msch.helpapp.adapters.EdFriendsAdapter
import com.msch.helpapp.adapters.EdImagesAdapter
import com.msch.helpapp.data.EventDetailsRepo
import com.msch.helpapp.domain.EventDetails
import com.msch.helpapp.implementations.*
import com.msch.helpapp.implementations.TimeWorks.calculateEstimatedTime
import com.msch.helpapp.interactors.*
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import javax.inject.Inject

class EventDetailsActivity : AppCompatActivity() {
    private var eventInfo: List<EventDetails> = ArrayList()
    private lateinit var eventTime: String
    private val lifecycleScope = MainScope()
    private var eventPosition = 0

    @Inject
    lateinit var interactors: Interactors


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ac_event_details)
        interactors = Interactors(
            AddEvents(EventDetailsRepo(EventDetailsDsImpl())),
            FilterNews(FilterNewsImpl()),
            AddCategories(CategoryItemsDsImpl()),
            GetUserInfo(GetUserInfoImpl()),
            GenerateSearchResults(SearchResultsGenerator()),
            FirebaseLogin(FirebaseLoginImpl()),
            FirebaseReg(FirebaseRegImpl())
        )

        lifecycleScope.launch {
            withContext(IO) {
                eventInfo = interactors.addEvents()
                eventTime = calculateEstimatedTime(eventInfo[eventPosition].eventDate, applicationContext)
            }


            val eId = intent.getStringExtra("ID")
            eventPosition = eventInfo.indexOfFirst { it.eventId == eId }

            findViewById<TextView>(R.id.ed_title).text = eventInfo[eventPosition].eventName
            findViewById<TextView>(R.id.ed_subtitle).text = eventInfo[eventPosition].eventName
            findViewById<TextView>(R.id.ed_event_date).text = eventTime
            findViewById<TextView>(R.id.ed_organizer).text = eventInfo[eventPosition].eventOrganizer
            findViewById<TextView>(R.id.ed_location).text = eventInfo[eventPosition].eventLocation
            findViewById<TextView>(R.id.ed_phones).text = eventInfo[eventPosition].eventContacts
            findViewById<TextView>(R.id.ed_description).text = eventInfo[eventPosition].eventDescription

            val imagesRecycler: RecyclerView = findViewById(R.id.ed_images_recycler)
            val imagesAdapter = EdImagesAdapter()
            imagesRecycler.layoutManager =
                LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)
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