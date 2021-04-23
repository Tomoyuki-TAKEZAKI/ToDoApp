package jp.takezaki.todo

import androidx.room.TypeConverter
import java.time.LocalDateTime
import java.time.ZoneOffset

class DateTimeConverter {

    @TypeConverter
    fun fromTimeStamp(value: Long?): LocalDateTime? =
        value?.let {
            LocalDateTime.ofEpochSecond(it, 0, ZoneOffset.UTC)
        }

    @TypeConverter
    fun toTimeStamp(date: LocalDateTime?): Long? =
        date?.toEpochSecond(ZoneOffset.UTC)
}