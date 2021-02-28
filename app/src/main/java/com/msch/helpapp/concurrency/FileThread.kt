package com.msch.helpapp.concurrency

import android.content.Context
import com.msch.helpapp.objects.JsonParser
import java.lang.reflect.Type

object FileThread {
    fun fileWorksThreadNoCoroutine(context: Context, listType: Type, filename: String): List<Any> {
        FileCoroutine.logThread("fileWorksThread")
        val fileContent: String = JsonParser.openFile(context.assets.open(filename))
        return JsonParser.parseJson(fileContent, listType)
    }
}