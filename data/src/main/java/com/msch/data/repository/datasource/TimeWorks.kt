package com.msch.data.repository.datasource

import android.content.Context
import com.msch.data.R
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
        return if (estimatedTime.days >= 0) {
            ("${context.resources.getQuantityString(R.plurals.days_left_plurals, estimatedTime.days)} ${estimatedTime.days} ${context.resources.getQuantityString(R.plurals.days_plurals, estimatedTime.days)} (${eventDate.get(DateTimeFieldType.dayOfMonth())}" +
                    ".${eventDate.get(DateTimeFieldType.monthOfYear())}" + ".${eventDate.get(DateTimeFieldType.year())})")
        } else {
            (context.resources.getString(R.string.nf_event_finished) + " (${eventDate.get(DateTimeFieldType.dayOfMonth())}" +
                    ".${eventDate.get(DateTimeFieldType.monthOfYear())}" + ".${eventDate.get(DateTimeFieldType.year())})")
        }
    }
}