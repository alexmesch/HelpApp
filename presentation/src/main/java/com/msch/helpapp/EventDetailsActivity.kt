package com.msch.helpapp

import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.widget.Button
import android.widget.FrameLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.msch.data.model.EventDetails
import com.msch.helpapp.adapters.EdFriendsAdapter
import com.msch.helpapp.adapters.EdImagesAdapter
import com.msch.domain.TimeWorks.calculateEstimatedTime
import com.msch.helpapp.presenters.EventDetailsPresenter
import com.msch.helpapp.views.EventDetailsView
import io.reactivex.Observable
import kotlinx.android.synthetic.main.ac_event_details.*
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter

class EventDetailsActivity : MvpAppCompatActivity(), EventDetailsView {
    private lateinit var eventInfo: List<EventDetails>

    private val lifecycleScope = MainScope()

    @InjectPresenter(presenterId = "edPresenter")
    lateinit var edPresenter: EventDetailsPresenter

    @ProvidePresenter
    fun provideEdPresenter(): EventDetailsPresenter {
        return EventDetailsPresenter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ac_event_details)
        val view: View = this.findViewById(android.R.id.content)

        view.findViewById<Button>(R.id.ed_back_btn).setOnClickListener{finishActivity(view)}
        provideEdPresenter()
       // lifecycleScope.launch {
            //withContext(IO) {
                eventInfo = edPresenter.getEvents()
            //}
            edPresenter.displayEvents(eventInfo)
            switchLoadingScreen(view)
        //}
    }

    private fun finishActivity(view: View) {
        finish()
    }

    override fun displayEvents(event: List<EventDetails>) {
        val eventPosition = event.indexOfFirst { it.eventId == intent.getStringExtra("ID") }

        val imagesAdapter = EdImagesAdapter()
        this.ed_images_recycler.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)
        this.ed_images_recycler.adapter = imagesAdapter
        event[eventPosition].eventSecondaryImages?.let { imagesAdapter.submitList(it) }

        this.ed_title.text = event[eventPosition].eventName
        this.ed_subtitle.text = event[eventPosition].eventName
        this.ed_event_date.text = event[eventPosition].eventDate?.let { calculateEstimatedTime(it,this) }
        this.ed_organizer.text = event[eventPosition].eventOrganizer
        this.ed_location.text = event[eventPosition].eventLocation
        this.ed_phones.text = event[eventPosition].eventContacts
        this.ed_description.text = event[eventPosition].eventDescription

        val friendsAdapter = EdFriendsAdapter()
        this.ed_friends_recycler.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)
        this.ed_friends_recycler.adapter = friendsAdapter
        event[eventPosition].eventFriends?.let { friendsAdapter.submitList(it) }
    }

    private fun switchLoadingScreen(view: View) {
        view.findViewById<FrameLayout>(R.id.ed_loadingScreen).visibility = GONE
    }
}