package com.msch.helpapp.concurrency

import android.content.Context
import com.msch.helpapp.objects.JsonParser
import java.lang.reflect.Type

object FileCoroutine {

    suspend fun fileWorksThread(context: Context, listType: Type, filename: String): List<Any> {
        logThread("fileWorksThread")
        val fileContent: String = JsonParser.openFile(context.assets.open(filename))
        return JsonParser.parseJson(fileContent, listType)
    }

    fun logThread(method: String) {
        println("debug: ${method}: ${Thread.currentThread().name}")
    }
}