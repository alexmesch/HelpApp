package com.msch.helpapp.presenters

import com.msch.domain.interactor.GetCategoryItemsUseCase
import com.msch.domain.model.CategoryItems
import com.msch.helpapp.views.HelpView
import io.reactivex.Single
import moxy.InjectViewState
import moxy.MvpPresenter
import javax.inject.Inject

@InjectViewState
class HelpPresenter @Inject constructor(private val useCase : GetCategoryItemsUseCase): MvpPresenter<HelpView>() {
    fun getObservable(): Single<List<CategoryItems>> {
        return useCase.execute()
    }

    fun showCategories(list: List<CategoryItems>) {
        viewState.displayCategories(list)
    }
}