package com.pavelwintercompany.rur_190.extensions

import android.content.res.Resources

    fun Int.toDp(): Int = (this / Resources.getSystem().displayMetrics.density).toInt()

    fun Int.dpToPx(): Int =(this * Resources.getSystem().displayMetrics.density).toInt()