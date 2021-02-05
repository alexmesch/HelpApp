package com.msch.helpapp.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.msch.helpapp.EventDetailsActivity
import com.msch.helpapp.R
import com.msch.helpapp.models.EventDetails
import com.msch.helpapp.models.NewsItem
import com.msch.helpapp.objects.TimeWorks.calculateEstimatedTime
import kotlinx.android.synthetic.main.nf_recycler_item.view.*

class NewsAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var items: List<EventDetails> = ArrayList()

    inner class NewsViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val eventTitle = itemView.nfi_event_title
        private val eventPic = itemView.nfi_event_image
        private val eventDesc = itemView.nfi_event_description
        private val eventTime = itemView.nfi_time_left

        init {
            itemView.setOnClickListener {
                val intent = Intent(itemView.context, EventDetailsActivity::class.java)
                intent.putExtra("ID",items[position].eventId)
                itemView.context.startActivity(intent)
            }
        }

        fun bind(newsItem: EventDetails, context: Context) {
            eventTitle.text = newsItem.eventName
            eventPic.setImageResource(context.resources.getIdentifier(newsItem.eventMainImage,"drawable",context.packageName))
            eventDesc.text = newsItem.eventDescription
            eventTime.text = calculateEstimatedTime(newsItem.eventDate)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return NewsViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.nf_recycler_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is NewsViewHolder -> {
                holder.bind(items[position],holder.itemView.context)
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun submitList(newsList : List<EventDetails>) {
        items = newsList
    }
}