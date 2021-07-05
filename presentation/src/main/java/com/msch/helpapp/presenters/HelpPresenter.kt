package com.msch.helpapp.presenters

import com.msch.data.datasource.CategoryItemsDS
import com.msch.domain.model.CategoryItems
import com.msch.helpapp.views.HelpView
import io.reactivex.Single
import moxy.InjectViewState
import moxy.MvpPresenter
import javax.inject.Inject

@InjectViewState
class HelpPresenter @Inject constructor(private var cat: CategoryItemsDS): MvpPresenter<HelpView>() {
    fun getObservable(): Single<List<CategoryItems>> {
        return cat.getCatObservable()
    }

    fun showCategories(list: List<CategoryItems>) {
        viewState.displayCategories(list)
    }
}