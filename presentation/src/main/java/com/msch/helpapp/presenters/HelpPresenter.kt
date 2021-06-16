package com.msch.helpapp.presenters

import com.msch.data.model.CategoryItems
import com.msch.helpapp.dagger.components.DaggerDataComponent
import com.msch.helpapp.views.HelpView
import moxy.MvpPresenter

class HelpPresenter: MvpPresenter<HelpView>() {
    private val cdComponent = DaggerDataComponent.builder().build().getCDComponent().catItemsDS()

    fun getCategories(): List<CategoryItems> {
        return cdComponent.addCategories()
    }

    fun showCategories(list: List<CategoryItems>) {
        viewState.displayCategories(list)
    }
}