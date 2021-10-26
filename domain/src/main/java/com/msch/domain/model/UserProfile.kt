package com.msch.domain.model

data class UserProfile(
    val birthday: String? = null,
    val friends: List<String> ? = null,
    val name: String? = null,
    val occupation: String? = null,
    val profilePic: String? = null
)