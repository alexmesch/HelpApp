package com.msch.helpapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.msch.helpapp.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.ed_photo_recycler_item.view.*

class EdImagesAdapter : RecyclerView.Adapter<EdImagesAdapter.ImagesViewHolder>() {

    private var items: List<String> = ArrayList()

    inner class ImagesViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val edImage = itemView.ed_image

        fun bind(imageItem: List<String?>) {
            Picasso.get().load(imageItem[adapterPosition]).into(edImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImagesViewHolder {
        return ImagesViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.ed_photo_recycler_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ImagesViewHolder, position: Int) {
        holder.bind(items)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun submitList(imagesList : List<String>) {
        items = imagesList
    }
}