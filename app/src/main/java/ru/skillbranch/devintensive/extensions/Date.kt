package ru.skillbranch.devintensive.extensions


import android.content.PeriodicSync
import java.text.SimpleDateFormat
import java.time.*
import java.time.temporal.ChronoUnit
import java.time.temporal.TemporalUnit
import java.util.*
import java.util.concurrent.TimeUnit


const val SECONDS = 1000L
const val MINUTE = 60 * SECONDS
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR

fun Date.format(pattern: String = "HH:mm:ss dd.mm.yy"): String {
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.add(value: Int, units: TimeUnits): Date {
    var time = this.time
    time += when (units) {
        TimeUnits.SECONDS -> value * SECONDS
        TimeUnits.MINUTE -> value * MINUTE
        TimeUnits.HOUR -> value * HOUR
        TimeUnits.DAY -> value * DAY
    }

    this.time = time
    return this
}

fun Date.humanizeDiff(date: Date?): Int {
    val newDate: LocalDateTime = LocalDateTime.now()
    var pp:LocalDateTime? = date?.toInstant()?.atZone(ZoneId.systemDefault())?.toLocalDateTime()
    var period: Long = pp?.until(newDate,ChronoUnit.DAYS)!!
    return period.toInt()
}




enum class TimeUnits {
    SECONDS,
    MINUTE,
    HOUR,
    DAY,
}

fun Date.plural(date: Date?): String {
    var period: Int = Date().humanizeDiff(date)
    var dayString: String = " дней назад"
    when (period) {
        0 -> return " сегодня"
        1 -> return " вчера"
        else -> {
            if (period < 0) return " - ошибка даты "
            else if (period / 10 != 1) {
                dayString = when (period % 10) {
                    1 -> " день назад"
                    2, 3, 4 -> " дня назад"
                    else -> " дней назад"
                }
            }
        }
    }
    return (period.toString() + dayString)
}



