package com.msch.helpapp.implementations

import com.msch.helpapp.interactors.*

data class Interactors(
    val addEvents: AddEvents,
    val filterNews: FilterNews,
    val addCategories: AddCategories,
    val getUserInfo: GetUserInfo,
    val generateSearchResults: GenerateSearchResults,
    val firebaseLogin: FirebaseLogin,
    val firebaseReg: FirebaseReg
)