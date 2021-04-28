package com.msch.helpapp.models

import java.util.*

data class UserProfile(
    var birthday: String = "",
    var friends: List<String> = ArrayList(),
    var name: String = "",
    var occupation: String = "",
    var profilePic: String = ""
)