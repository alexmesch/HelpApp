package com.msch.helpapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.msch.helpapp.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.ed_photo_recycler_item.view.*

class EdImagesAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: List<String> = ArrayList()

    inner class ImagesViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
        val edImage = itemView.ed_image

        fun bind(imageItem: List<String>, context: Context) {
            Picasso.get().load(imageItem[position]).into(edImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ImagesViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.ed_photo_recycler_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is ImagesViewHolder -> {
                holder.bind(items, holder.itemView.context)
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun submitList(imagesList : List<String>) {
        items = imagesList
    }
}