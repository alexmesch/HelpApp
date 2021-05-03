package com.msch.helpapp.implementations

import com.msch.helpapp.data.FilterNews
import com.msch.helpapp.domain.EventDetails

class FilterNewsImpl: FilterNews {
    override fun filterNews(news: List<EventDetails>, filter: String?): List<EventDetails> {
        return news.filter { filter == null || it.eventCategory == filter}
    }
}