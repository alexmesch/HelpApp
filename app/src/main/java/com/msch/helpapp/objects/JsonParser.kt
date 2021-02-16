package com.msch.helpapp.objects

import com.google.gson.*
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.msch.helpapp.models.CategoryItems
import com.msch.helpapp.models.EventDetails
import java.io.BufferedReader
import java.io.InputStream


object JsonParser {
    private lateinit var model: String
    fun openFile(filePath: InputStream): String  {
        val bufferedReader: BufferedReader = filePath.bufferedReader()
        return bufferedReader.use { it.readText()}
    }

    fun parseCategoriesJson(fileContent: String): List<CategoryItems> {
        return GsonBuilder()
                .create()
                .fromJson(fileContent, Array<CategoryItems>::class.java)
                .toList()
    }

    fun parseEventDetails(fileContent: String): List<EventDetails> {
        return GsonBuilder()
                .create()
                .fromJson(fileContent, Array<EventDetails>::class.java)
                .toList()
    }
}