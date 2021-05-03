package com.msch.helpapp.interactors

import com.msch.helpapp.data.FilterNews
import com.msch.helpapp.domain.EventDetails

class FilterNews(private val filterNews: FilterNews) {
    operator fun invoke(news: List<EventDetails>, filter: String?) = filterNews.filterNews(news, filter )
}