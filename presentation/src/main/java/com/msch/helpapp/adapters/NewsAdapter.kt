package com.msch.helpapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.msch.data.repository.datasource.TimeWorks.calculateEstimatedTime
import com.msch.domain.model.EventDetails
import com.msch.helpapp.R
import kotlinx.android.synthetic.main.nf_recycler_item.view.*

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {
    private var items: List<EventDetails> = ArrayList()

    private var listener: AdapterListener? = null

    interface AdapterListener {
        fun onItemClicked(id: String?)
    }

    fun setListener(listener: AdapterListener?) {
        this.listener = listener
    }

    inner class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val eventTitle = itemView.nfi_event_title
        private val eventPic = itemView.nfi_event_image
        private val eventDesc = itemView.nfi_event_description
        private val eventTime = itemView.nfi_time_left

        init {
            itemView.setOnClickListener {
                listener?.onItemClicked(items[adapterPosition].eventId)
            }
        }

        fun bind(newsItem: EventDetails, context: Context) {
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

    fun submitList(newsList: List<EventDetails>) {
        items = newsList
    }
}