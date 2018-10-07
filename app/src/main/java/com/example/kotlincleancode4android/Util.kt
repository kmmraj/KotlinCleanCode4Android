package com.example.kotlincleancode4android

import java.util.*
import java.util.concurrent.TimeUnit

/**
 * Created by Mohanraj Karatadipalayam on 06/10/18.
 */

class ArrayEmptyException(var msg: String? =  "ArrayEmptyException") : RuntimeException()

object CalendarUtil {

    fun getCalendar(date: String?): Calendar {
        //Date should be in the format YYYY/MM/DD if not return
        if (date != null && !date.isEmpty() && date.length == 10) {
            val year = Integer.parseInt(date.substring(0, 4))
            val month = Integer.parseInt(date.substring(5, 7))
            val day = Integer.parseInt(date.substring(8, 10))
            val startingTime = Calendar.getInstance()
            startingTime.set(year, month - 1, day, 0, 0, 0)
            return startingTime
        }
        return Calendar.getInstance()
    }

    fun getDaysDiff(startTime: Long, endTime: Long): Long {
        val msDiff: Long
        if (endTime > startTime) {
            msDiff = endTime - startTime
        } else {
            msDiff = startTime - endTime
        }
        // Log.e(TAG,"diff is  "+ daysDiff);
        return TimeUnit.MILLISECONDS.toDays(msDiff)
    }
}

