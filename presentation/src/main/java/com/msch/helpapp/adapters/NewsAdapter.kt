package com.msch.helpapp.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.msch.data.datasource.TimeWorks.calculateEstimatedTime
import com.msch.helpapp.EventDetailsActivity
import com.msch.helpapp.R
import kotlinx.android.synthetic.main.nf_recycler_item.view.*

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {
    private var items: List<com.msch.domain.model.EventDetails> = ArrayList()

    inner class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val eventTitle = itemView.nfi_event_title
        private val eventPic = itemView.nfi_event_image
        private val eventDesc = itemView.nfi_event_description
        private val eventTime = itemView.nfi_time_left

        init {
            itemView.setOnClickListener {
                val intent = Intent(itemView.context, EventDetailsActivity::class.java)
                intent.putExtra("ID", items[position].eventId)
                itemView.context.startActivity(intent)
            }
        }

        fun bind(newsItem: com.msch.domain.model.EventDetails, context: Context) {
            eventTitle.text = newsItem.eventName
            eventPic.setImageResource(
                context.resources.getIdentifier(
                    newsItem.eventMainImage,
                    "drawable",
                    context.packageName
                )
            )
            eventDesc.text = newsItem.eventDescription
            eventTime.text = newsItem.eventDate?.let { calculateEstimatedTime(it, context) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.nf_recycler_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(items[position], holder.itemView.context)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun submitList(newsList: List<com.msch.domain.model.EventDetails>) {
        items = newsList
    }
}