package com.msch.domain

import java.util.regex.Matcher
import java.util.regex.Pattern

object EmailValidator {
    fun isEmailValid(email: String): Boolean {
        val exp = "^[\\w.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$"
        val pattern: Pattern = Pattern.compile(exp, Pattern.CASE_INSENSITIVE)
        val matcher: Matcher = pattern.matcher(email)
        return matcher.matches()
    }
}