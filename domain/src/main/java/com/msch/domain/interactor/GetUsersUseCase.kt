package com.msch.domain.interactor

import com.msch.domain.PerFragment
import com.msch.domain.model.UserProfile
import com.msch.domain.repository.DataRepository
import io.reactivex.Single
import javax.inject.Inject

@PerFragment
class GetUsersUseCase @Inject constructor(private val repo: DataRepository) {
    fun execute(userID: String): Single<UserProfile> {
        return repo.getUserSingle(userID)
    }
}