package com.msch.helpapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.msch.helpapp.R
import kotlinx.android.synthetic.main.ed_friends_recycler_item.view.*

class EdFriendsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: List<String> = ArrayList()

    inner class ImagesViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
        val edf_image = itemView.edf_image

        fun bind(imageItem: List<String>, context: Context) {
            edf_image.setImageResource(context.resources.getIdentifier(imageItem[position],"drawable",context.packageName))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ImagesViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.ed_friends_recycler_item, parent, false)
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