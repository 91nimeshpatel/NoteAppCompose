package com.nimeshpatel.noteapp.util

import androidx.room.TypeConverter
import java.util.Date

/**
 * Created by Nimesh Patel on 14-Sep-24.
 * Purpose:
 */
class DateTypeConverter {

    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }
}