package com.msch.helpapp.objects

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.msch.helpapp.models.CategoryItems
import com.msch.helpapp.models.EventDetails
import java.io.BufferedReader
import java.io.InputStream


object JsonParser {
    fun openFile(filePath: InputStream): String  {
        val bufferedReader: BufferedReader = filePath.bufferedReader()
        return bufferedReader.use { it.readText() }
    }

    fun parseCategoriesJson(fileContent: String): List<CategoryItems> {
        val builder = GsonBuilder()
        val gson = builder.create()
        return gson.fromJson(fileContent, Array<CategoryItems>::class.java).toList()
    }

    fun parseEventDetails(fileContent: String): List<EventDetails> {
        val builder = GsonBuilder()
        val gson = builder.create()
        return gson.fromJson(fileContent, Array<EventDetails>::class.java).toList()
    }
}