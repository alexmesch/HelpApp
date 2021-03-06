package com.msch.helpapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.msch.helpapp.R
import com.msch.helpapp.models.FriendsInfo
import kotlinx.android.synthetic.main.pf_recycler_item.view.*

class FriendsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var friendsItems: List<FriendsInfo> = ArrayList()

    inner class FriendsViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val friendsImage = itemView.friend_profile_pic
        private val friendsName = itemView.friend_name


        fun bind(friendsItems: FriendsInfo) {
            friendsName.setText(friendsItems.friendName)
            friendsImage.setImageResource(friendsItems.friendPic)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return FriendsViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.pf_recycler_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is FriendsViewHolder -> {
                holder.bind(friendsItems[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return friendsItems.size
    }

    fun submitList(friendsList : List<FriendsInfo>) {
        friendsItems = friendsList
    }
}