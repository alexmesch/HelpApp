package com.msch.helpapp.presenter

import com.msch.helpapp.DI.DependencyInjector
import com.msch.helpapp.domain.CategoryItems
import com.msch.helpapp.view.contracts.CategoryContract

class CategoryPresenter (view: CategoryContract.CategoryView, dependencyInjector: DependencyInjector): CategoryContract.CategoryPresenter {
    private val categoryItems = dependencyInjector.categoryDs()
    private var view: CategoryContract.CategoryView? = view

    override fun onDestroy() {
        this.view = null
    }

    override fun getCategories(): List<CategoryItems> {
        return categoryItems.addCategories()
    }
}