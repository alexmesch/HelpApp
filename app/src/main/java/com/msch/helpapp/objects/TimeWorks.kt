package com.msch.helpapp.objects

import android.content.Context
import com.msch.helpapp.R
import org.joda.time.DateTime
import org.joda.time.DateTimeFieldType
import org.joda.time.Period
import org.joda.time.PeriodType
import org.joda.time.format.DateTimeFormat
import org.joda.time.format.DateTimeFormatter

object TimeWorks {
    private val dateFormat: DateTimeFormatter = DateTimeFormat.forPattern("dd.MM.yyyy")
    fun calculateEstimatedTime(date: String, context: Context): String {
        val currentDate = DateTime.now()
        val eventDate = dateFormat.parseDateTime(date)
        val estimatedTime = Period(currentDate, eventDate, PeriodType.days())
        if (estimatedTime.days >= 0) {
            return ("${context.resources.getQuantityString(R.plurals.days_left_plurals, estimatedTime.days)} ${estimatedTime.days} ${context.resources.getQuantityString(R.plurals.days_plurals, estimatedTime.days)} (${eventDate.get(DateTimeFieldType.dayOfMonth())}" +
                    ".${eventDate.get(DateTimeFieldType.monthOfYear())}" + ".${eventDate.get(DateTimeFieldType.year())})")
        } else {
            return (context.resources.getString(R.string.nf_event_finished) + " (${eventDate.get(DateTimeFieldType.dayOfMonth())}" +
                    ".${eventDate.get(DateTimeFieldType.monthOfYear())}" + ".${eventDate.get(DateTimeFieldType.year())})")
        }
    }
}