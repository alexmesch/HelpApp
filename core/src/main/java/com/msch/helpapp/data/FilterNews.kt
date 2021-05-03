package com.msch.helpapp.data

import com.msch.helpapp.domain.EventDetails

interface FilterNews {
    fun filterNews(news: List<EventDetails>, filter: String?): List<EventDetails>
}