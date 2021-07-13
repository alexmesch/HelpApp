package com.msch.domain.interactor

import com.msch.domain.model.UserProfile
import com.msch.domain.repository.DataRepository
import io.reactivex.Single

class GetUsersUseCase(private val repo: DataRepository) {
    fun execute(userID: String): Single<UserProfile> {
        return repo.getUserDS(userID)
    }
}