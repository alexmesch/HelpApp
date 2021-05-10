package com.msch.helpapp.DI

import com.msch.helpapp.data.authentication.FirebaseLogin
import com.msch.helpapp.data.authentication.FirebaseReg
import com.msch.helpapp.data.datasource.CategoryItemsDS
import com.msch.helpapp.data.datasource.EventDetailsDS
import com.msch.helpapp.data.datasource.UserInfoDS
import com.msch.helpapp.implementations.*

class DependencyInjectorImpl: DependencyInjector {
    override fun eventDs(): EventDetailsDS {
        return EventDetailsDsImpl()
    }

    override fun categoryDs(): CategoryItemsDS {
        return CategoryItemsDsImpl()
    }

    override fun login(): FirebaseLogin {
        return FirebaseLoginImpl()
    }

    override fun register(): FirebaseReg {
        return FirebaseRegImpl()
    }

    override fun userInfo(): UserInfoDS {
        return GetUserInfoImpl()
    }
}