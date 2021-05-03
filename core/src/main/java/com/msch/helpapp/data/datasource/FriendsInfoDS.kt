package com.msch.helpapp.data.datasource

import com.msch.helpapp.domain.FriendsInfo

interface FriendsInfoDS {
    fun read(friendsInfo: FriendsInfo)
}