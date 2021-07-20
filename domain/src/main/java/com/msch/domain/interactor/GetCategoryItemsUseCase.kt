package com.msch.domain.interactor

import com.msch.domain.model.CategoryItems
import com.msch.domain.repository.DataRepository
import io.reactivex.Single

class GetCategoryItemsUseCase(private val repo: DataRepository){
    fun execute(): Single<List<CategoryItems>> {
        return repo.getCategoriesSingle()
    }
}