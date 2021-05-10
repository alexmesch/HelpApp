package com.msch.helpapp

import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.msch.helpapp.DI.DependencyInjectorImpl
import com.msch.helpapp.view.adapters.EdFriendsAdapter
import com.msch.helpapp.view.adapters.EdImagesAdapter
import com.msch.helpapp.domain.EventDetails
import com.msch.helpapp.implementations.TimeWorks.calculateEstimatedTime
import com.msch.helpapp.presenter.EventsPresenter
import com.msch.helpapp.view.contracts.EventsContract
import kotlinx.android.synthetic.main.ac_event_details.*
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO

class EventDetailsActivity : AppCompatActivity(), EventsContract.EventsView {
    private lateinit var eventInfo: List<EventDetails>
    private val lifecycleScope = MainScope()
    private lateinit var presenter: EventsContract.EventsPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ac_event_details)
        setPresenter(EventsPresenter(this, DependencyInjectorImpl()))
        val view: View = this.findViewById(android.R.id.content)

        lifecycleScope.launch {
            withContext(IO) {
                eventInfo = presenter.getEvents("null")
            }
            displayEvents(eventInfo)
            switchLoadingScreen(view)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
        lifecycleScope.cancel()
    }

    fun finishActivity(view: View) {
        finish()
    }

    override fun setPresenter(presenter: EventsContract.EventsPresenter) {
        this.presenter = presenter
    }

    override fun displayEvents(event: List<EventDetails>) {
        val eventPosition = event.indexOfFirst { it.eventId == intent.getStringExtra("ID") }

        val imagesAdapter = EdImagesAdapter()
        this.ed_images_recycler.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)
        this.ed_images_recycler.adapter = imagesAdapter
        imagesAdapter.submitList(event[eventPosition].eventSecondaryImages)

        this.ed_title.text = event[eventPosition].eventName
        this.ed_subtitle.text = event[eventPosition].eventName
        this.ed_event_date.text = calculateEstimatedTime(event[eventPosition].eventDate,this)
        this.ed_organizer.text = event[eventPosition].eventOrganizer
        this.ed_location.text = event[eventPosition].eventLocation
        this.ed_phones.text = event[eventPosition].eventContacts
        this.ed_description.text = event[eventPosition].eventDescription

        val friendsAdapter = EdFriendsAdapter()
        this.ed_friends_recycler.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)
        this.ed_friends_recycler.adapter = friendsAdapter
        friendsAdapter.submitList(event[eventPosition].eventFriends)
    }

    private fun switchLoadingScreen(view: View) {
        view.findViewById<FrameLayout>(R.id.ed_loadingScreen).visibility = GONE
    }
}