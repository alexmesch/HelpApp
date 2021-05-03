package com.msch.helpapp.data.datasource

import com.msch.helpapp.domain.UserProfile

interface UserInfoDS {
    fun getUserInfo(userID: String): UserProfile
}