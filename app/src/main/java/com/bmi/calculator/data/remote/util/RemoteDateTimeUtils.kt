package com.bmi.calculator.data.remote.util

import java.text.SimpleDateFormat
import java.util.*

object RemoteDateTimeUtils {

    private const val SERVER_DATE_PATTERN: String = "yyyy-MM-dd"
    private const val SERVER_DATE_TIME_PATTERN: String = "yyyy-MM-dd HH:mm:ss"

    //region Formatter
    fun formatServerDate(date: Date): String {
        return SimpleDateFormat(SERVER_DATE_PATTERN, Locale.getDefault()).format(date)
    }

    fun formatServerDate(millis: Long): String {
        return SimpleDateFormat(SERVER_DATE_PATTERN, Locale.getDefault()).format(Date(millis))
    }

    fun formatServerDateTime(date: Date): String {
        return SimpleDateFormat(SERVER_DATE_TIME_PATTERN, Locale.getDefault()).format(date)
    }

    fun formatServerDateTime(millis: Long): String {
        return SimpleDateFormat(SERVER_DATE_TIME_PATTERN, Locale.getDefault()).format(Date(millis))
    }

    fun getCurrentDate() : Long = Date().time
    //endregion Formatter

    //region Parser
    fun parseServerDate(dateString: String?): Long? {
        return try {
            dateString ?: throw Exception()
            val formatter = SimpleDateFormat(SERVER_DATE_PATTERN, Locale.getDefault())
            formatter.parse(dateString)?.time
        } catch (ex: Exception) {
            ex.printStackTrace()
            null
        }
    }

    fun parseServerDatetime(dateString: String?): Long? {
        return try {
            dateString ?: throw Exception()
            val formatter = SimpleDateFormat(SERVER_DATE_TIME_PATTERN, Locale.getDefault())
            formatter.parse(dateString)?.time
        } catch (ex: Exception) {
            ex.printStackTrace()
            null
        }
    }
    //endregion Parser

}