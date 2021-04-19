package com.msch.helpapp.data

import com.msch.helpapp.models.SearchInfo
import kotlin.collections.ArrayList

class SearchSource {
    companion object {
        fun createSearchData(): ArrayList<SearchInfo> {
            val list = ArrayList<SearchInfo>()
            val listSize: Int = (1..15).random()

            for (i in 0..listSize) {
                list.add(SearchInfo(randomizeString((1..20).random())))
            }

            return list
        }
        private fun randomizeString(length: Int): String {
            val charPool : List<Char> = ('a'..'z') + ('A'..'Z') + ('а'..'я') + ('А'..'Я') + ('0'..'9')
            return (1..length)
                .map{kotlin.random.Random.nextInt(0,charPool.size)}
                .map(charPool::get)
                .joinToString("")
        }
    }
}