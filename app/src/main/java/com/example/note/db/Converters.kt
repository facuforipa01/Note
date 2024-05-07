package com.example.note.db

import androidx.room.TypeConverter
import java.util.Date

class Converters {
    @TypeConverter
    //dato que viene de la base de datos, de timestamp a date
    fun FromTimeStamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    //de tipo date a timestamp, que viene del front
    fun dateToTimeStamp(date: Date?): Long? {
        return date?.time?.toLong()
    }
}