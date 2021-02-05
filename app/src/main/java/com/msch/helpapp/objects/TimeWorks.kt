package com.msch.helpapp.objects

import org.joda.time.DateTime
import org.joda.time.DateTimeFieldType
import org.joda.time.Period
import org.joda.time.PeriodType
import org.joda.time.format.DateTimeFormat
import org.joda.time.format.DateTimeFormatter

object TimeWorks {
    fun calculateEstimatedTime(date: String): String {
        val dateFormat: DateTimeFormatter = DateTimeFormat.forPattern("dd.MM.yyyy")
        val currentDate = DateTime.now()
        val eventDate = dateFormat.parseDateTime(date)
        val estimatedTime = Period(currentDate, eventDate, PeriodType.days())
        if (estimatedTime.days >= 0) {
            return ("Осталось ${estimatedTime.days} дней (${eventDate.get(DateTimeFieldType.dayOfMonth())}" +
                    ".${eventDate.get(DateTimeFieldType.monthOfYear())})")
        }
        else {
            return "Мероприятие завершено"
        }
    }
}