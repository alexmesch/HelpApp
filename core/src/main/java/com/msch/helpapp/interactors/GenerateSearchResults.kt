package com.msch.helpapp.interactors

import com.msch.helpapp.data.SearchResultGen

class GenerateSearchResults(private val searchResultImpl: SearchResultGen) {
    operator fun invoke() = searchResultImpl.generateSearchResults()
}