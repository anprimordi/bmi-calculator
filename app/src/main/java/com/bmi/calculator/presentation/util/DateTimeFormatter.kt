package com.bmi.calculator.presentation.util

import com.bmi.calculator.domain.model.common.Constants
import java.text.SimpleDateFormat
import java.util.*

object DateTimeFormatter {

    private const val DATE_TIME_PATTERN: String = "d MMMM yyyy HH:mm"
    private const val DATE_TIME_PATTERN_NOTIFICATION: String = "d MMMM yyyy, HH:mm"
    private const val DATE_PATTERN: String = "d MMMM yyyy"
    private const val TIME_PATTERN: String = "HH:mm"
    private const val YEAR_PATTERN: String = "yyyy"
    private const val FILE_NAME_PATTERN: String = "yyMMddHHmmss"

    fun get7DaysBeforeNow(): Date {
        val now = Calendar.getInstance()
        now.add(Calendar.WEEK_OF_YEAR, -1)
        return now.time
    }

    @JvmStatic
    fun getCurrentYear(): String {
        return SimpleDateFormat(YEAR_PATTERN, Locale.getDefault()).format(Date())
    }

    //region Formatter
    @JvmStatic
    fun formatDateTime(date: Date?): String {
        return if (date != null) SimpleDateFormat(DATE_TIME_PATTERN, Locale.getDefault()).format(
            date
        ) else Constants.DEFAULT_CONTENT
    }

    @JvmStatic
    fun formatDateTime(millis: Long?): String {
        return if (millis != null) SimpleDateFormat(DATE_TIME_PATTERN, Locale.getDefault()).format(
            Date(millis)
        ) else Constants.DEFAULT_CONTENT
    }

    @JvmStatic
    fun formatDateTimeNotification(date: Date?): String {
        return if (date != null) SimpleDateFormat(DATE_TIME_PATTERN_NOTIFICATION, Locale.getDefault()).format(
            date
        ) else Constants.DEFAULT_CONTENT
    }

    @JvmStatic
    fun formatDateTimeNotification(millis: Long?): String {
        return if (millis != null) SimpleDateFormat(DATE_TIME_PATTERN_NOTIFICATION, Locale.getDefault()).format(
            Date(millis)
        ) else Constants.DEFAULT_CONTENT
    }

    @JvmStatic
    fun formatDate(date: Date?): String {
        return if (date != null) SimpleDateFormat(
            DATE_PATTERN,
            Locale("ID")
        ).format(date) else Constants.DEFAULT_CONTENT
    }

    @JvmStatic
    fun formatDate(millis: Long?): String {
        return if (millis != null) SimpleDateFormat(DATE_PATTERN, Locale.getDefault()).format(
            Date(
                millis
            )
        ) else Constants.DEFAULT_CONTENT
    }

    @JvmStatic
    fun formatTime(date: Date?): String {
        return if (date != null) SimpleDateFormat(
            TIME_PATTERN,
            Locale.getDefault()
        ).format(date) else Constants.DEFAULT_CONTENT
    }

    @JvmStatic
    fun formatTime(millis: Long?): String {
        return if (millis != null) SimpleDateFormat(TIME_PATTERN, Locale.getDefault()).format(
            Date(
                millis
            )
        ) else Constants.DEFAULT_CONTENT
    }

    @JvmStatic
    fun formatFileName(date: Date?): String {
        return if (date != null) SimpleDateFormat(
            FILE_NAME_PATTERN,
            Locale.getDefault()
        ).format(date) else Constants.DEFAULT_CONTENT
    }

    @JvmStatic
    fun formatFileName(millis: Long?): String {
        return if (millis != null) SimpleDateFormat(FILE_NAME_PATTERN, Locale.getDefault()).format(
            Date(
                millis
            )
        ) else Constants.DEFAULT_CONTENT
    }
    //endregion Formatter


}