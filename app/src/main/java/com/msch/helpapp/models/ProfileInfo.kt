package com.msch.helpapp.models

import java.util.*
import kotlin.collections.ArrayList

data class ProfileInfo(
    var profileImage: Int,
    var profileName: String,
    var profileBirthday: Date,
    var profileOccupation: String,
    var profileNotifications: Boolean,
    var profileFriends: ArrayList<String>
) {
}