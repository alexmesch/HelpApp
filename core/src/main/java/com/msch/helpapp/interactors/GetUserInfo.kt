package com.msch.helpapp.interactors

import com.msch.helpapp.data.datasource.UserInfoDS

class GetUserInfo(private val userInfoImpl: UserInfoDS) {
    operator fun invoke(userID: String) = userInfoImpl.getUserInfo(userID)
}