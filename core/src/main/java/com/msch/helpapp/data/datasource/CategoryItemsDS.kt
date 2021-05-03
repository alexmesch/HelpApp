package com.msch.helpapp.data.datasource

import com.msch.helpapp.domain.CategoryItems

interface CategoryItemsDS {
    fun addCategories(): List<CategoryItems>
}