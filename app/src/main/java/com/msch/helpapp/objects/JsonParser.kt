package com.msch.helpapp.objects

import com.google.gson.GsonBuilder
import java.io.BufferedReader
import java.io.InputStream
import java.lang.reflect.Type


object JsonParser {
    fun openFile(filePath: InputStream): String  {
        val bufferedReader: BufferedReader = filePath.bufferedReader()
        return bufferedReader.use { it.readText()}
    }

    fun parseJson(fileContent: String, type: Type): List<Any> {
        return GsonBuilder()
                .create()
                .fromJson(fileContent, type)
    }
}