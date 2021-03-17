package com.pavelwintercompany.rur_190.utils

import java.text.SimpleDateFormat
import java.util.*

object DateHelper {

fun formattedTime(dateInMillisec : Long, formatString : String): String{
    val formatter = SimpleDateFormat(formatString)
    return formatter.format(Date(dateInMillisec))
}
}