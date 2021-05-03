package com.msch.helpapp.interactors

import com.msch.helpapp.data.datasource.CategoryItemsDS

class AddCategories(private val categoryItems: CategoryItemsDS) {
    operator fun invoke() = categoryItems.addCategories()
}