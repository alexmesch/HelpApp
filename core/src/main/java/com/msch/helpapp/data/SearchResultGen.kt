package com.msch.helpapp.data

import com.msch.helpapp.domain.SearchInfo

interface SearchResultGen {
    fun generateSearchResults(): ArrayList<SearchInfo>
}