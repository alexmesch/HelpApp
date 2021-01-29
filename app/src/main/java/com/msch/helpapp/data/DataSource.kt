package com.msch.helpapp.data

import com.msch.helpapp.R
import com.msch.helpapp.models.CategoryItem

class DataSource {
    companion object {
        fun createDataSet() : ArrayList<CategoryItem> {
            val list = ArrayList<CategoryItem>()
            list.add(
                CategoryItem("Дети", R.drawable.icon_kids)
            )
            list.add(
                CategoryItem("Взрослые", R.drawable.icon_adults)
            )
            list.add(
                CategoryItem("Пожилые", R.drawable.icon_elders)
            )
            list.add(
                CategoryItem("Животные", R.drawable.icon_animals)
            )
            list.add(
                CategoryItem("Мероприятия", R.drawable.icon_events)
            )
            return list
        }
    }
}