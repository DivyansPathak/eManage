package com.example.emanage.Model

import androidx.room.TypeConverter
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class Converters {

    private val dateFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy")

    @TypeConverter
    fun fromLocalDate(localDate: LocalDate?): String? {
        return localDate?.format(dateFormatter)
    }

    @TypeConverter
    fun toLocalDate(dateString: String?): LocalDate? {
        return dateString?.let { LocalDate.parse(it, dateFormatter) }
    }


//    @TypeConverter
//    fun fromTimestamp(value:String?) : LocalDateTime? {
//        return value?.let{LocalDateTime.parse(it)}
//    }
//
//    @TypeConverter
//    fun dateToTimestamp(date: LocalDateTime?):String?{
//        return date?.toString()
//    }
}
