package com.pavelwintercompany.rur_190.utils

import com.google.android.material.datepicker.MaterialDatePicker.Builder.datePicker
import java.text.SimpleDateFormat
import java.util.*


object DateHelper {

fun formattedTime(dateInMillisec: Long, formatString: String): String{
    val formatter = SimpleDateFormat(formatString)
    return formatter.format(Date(dateInMillisec))
}

    fun millisecFromDatetime(hour: Int): Long{

        val calendar = Calendar.getInstance()
        calendar[2021, 4, 25, hour, 0] = 0
        return calendar.timeInMillis

    }
}