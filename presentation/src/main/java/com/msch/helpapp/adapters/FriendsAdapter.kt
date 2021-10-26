package com.msch.helpapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.msch.helpapp.R
import kotlinx.android.synthetic.main.pf_recycler_item.view.*

class FriendsAdapter : RecyclerView.Adapter<FriendsAdapter.FriendsViewHolder>() {

    private var friendsItems: List<com.msch.domain.model.FriendsInfo> = ArrayList()

    inner class FriendsViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val friendsImage = itemView.friend_profile_pic
        private val friendsName = itemView.friend_name


        fun bind(friendsItems: com.msch.domain.model.FriendsInfo) {
            friendsName.setText(friendsItems.friendName)
            friendsImage.setImageResource(friendsItems.friendPic)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendsViewHolder {
        return FriendsViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.pf_recycler_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: FriendsViewHolder, position: Int) {
                holder.bind(friendsItems[position])
    }

    override fun getItemCount(): Int {
        return friendsItems.size
    }

    fun submitList(friendsList : List<com.msch.domain.model.FriendsInfo>) {
        friendsItems = friendsList
    }
}