package com.msch.helpapp.data

import com.msch.helpapp.R
import com.msch.helpapp.models.FriendsInfo

class FriendsSource {
    companion object {
        fun createFriendData(): ArrayList<FriendsInfo> {
            val list = ArrayList<FriendsInfo>()
            list.add(
                FriendsInfo("Дмитрий Валерьевич", R.drawable.valerevich)
            )
            list.add(
                FriendsInfo("Евгений Александров", R.drawable.alexandrov)
            )
            list.add(
                FriendsInfo("Виктор Кузнецов", R.drawable.kuznetsov)
            )
            list.add(
                FriendsInfo("ПростоЧтобыЧетыре", R.drawable.icon_animals)
            )
            return list
        }
    }
}