package com.msch.helpapp.presenters

import com.msch.domain.model.CategoryItems
import com.msch.helpapp.dagger.components.DataComponent
import com.msch.helpapp.views.HelpView
import io.reactivex.Single
import moxy.InjectViewState
import moxy.MvpPresenter
import javax.inject.Inject

@InjectViewState
class HelpPresenter: MvpPresenter<HelpView>() {
    @Inject
    fun getObservable(component: DataComponent): Single<List<CategoryItems>> {
        return component.getCDComponent().catItemsDS().getCatObservable()
    }

    fun showCategories(list: List<CategoryItems>) {
        viewState.displayCategories(list)
    }
}