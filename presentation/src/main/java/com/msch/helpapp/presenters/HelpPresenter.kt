package com.msch.helpapp.presenters

import com.msch.domain.model.CategoryItems
import com.msch.helpapp.dagger.components.DaggerDataComponent
import com.msch.helpapp.views.HelpView
import io.reactivex.Single
import moxy.MvpPresenter
import javax.inject.Inject

class HelpPresenter @Inject constructor(): MvpPresenter<HelpView>() {

    fun getObservable(): Single<List<CategoryItems>> {
        return DaggerDataComponent.create().getCDComponent().catItemsDS().getCatObservable()
    }

    fun showCategories(list: List<CategoryItems>) {
        viewState.displayCategories(list)
    }
}