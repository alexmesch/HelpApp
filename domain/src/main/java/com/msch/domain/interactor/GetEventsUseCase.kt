package com.msch.domain.interactor

import com.msch.domain.model.EventDetails
import com.msch.domain.repository.DataRepository
import io.reactivex.Single

class GetEventsUseCase (private val repo: DataRepository) {
    fun execute(): Single<List<EventDetails>> {
        return repo.getEventsSingle()
    }
}