package com.msch.helpapp.data

import com.msch.helpapp.R
import com.msch.helpapp.models.NewsItem
import java.util.*
import kotlin.collections.ArrayList

class NewsSource {
    companion object {
        fun createNewsData(): ArrayList<NewsItem> {
            val list = ArrayList<NewsItem>()
            list.add(
                    NewsItem("Спонсоры отремонтируют школу-интернат", R.drawable.cardimage,
                            "Дубовская школа-интернат для детей с ограниченными возможностями здоровья стала первой в области …",
                    "22.02.1999")
            )
            list.add(
                    NewsItem("Спонсоры не отремонтируют школу-интернат", R.drawable.cardimage,
                            "Разруха!",
                            "01.01.2020")
            )
            return list
        }
    }
}