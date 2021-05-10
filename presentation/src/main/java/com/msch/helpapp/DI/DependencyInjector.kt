package com.msch.helpapp.DI

import com.msch.helpapp.data.authentication.FirebaseLogin
import com.msch.helpapp.data.authentication.FirebaseReg
import com.msch.helpapp.data.datasource.CategoryItemsDS
import com.msch.helpapp.data.datasource.EventDetailsDS
import com.msch.helpapp.data.datasource.UserInfoDS


interface DependencyInjector {
    fun eventDs(): EventDetailsDS
    fun categoryDs(): CategoryItemsDS
    fun login(): FirebaseLogin
    fun register(): FirebaseReg
    fun userInfo(): UserInfoDS
}