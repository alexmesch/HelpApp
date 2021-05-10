package com.msch.helpapp.view.contracts

import com.msch.helpapp.domain.CategoryItems
import com.msch.helpapp.presenter.BasePresenter
import com.msch.helpapp.view.BaseView

interface CategoryContract {
    interface CategoryPresenter: BasePresenter {
        fun getCategories(): List<CategoryItems>
    }

    interface CategoryView: BaseView<CategoryPresenter> {
        fun displayCategories(categories: List<CategoryItems>)
    }
}