package ru.skillbranch.devintensive.extensions

import java.math.BigInteger
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.Period
import java.time.ZoneId
import java.util.*

const val SECONDS = 1000L
const val MINUTE = 60 * SECONDS
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR

fun Date.format(pattern:String = "HH:mm:ss dd.mm.yy"):String{
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}
fun Date.add(value:Int, units:TimeUnits ):Date{
    var time = this.time
    time += when(units){
      TimeUnits.SECONDS -> value * SECONDS
      TimeUnits.MINUTE -> value * MINUTE
      TimeUnits.HOUR -> value * HOUR
      TimeUnits.DAY -> value * DAY
    }

    this.time = time
    return this
}

fun Date.humanizeDiff(date:Date? ): String {
    val newDate:Date = Date()
    val period = Period.between(date?.toInstant()?.atZone(ZoneId.systemDefault())?.toLocalDate(),
                                        newDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate())
    val message: String = "Последнее посещение было " + period.days

// return date.toString()
    return message
}


enum class TimeUnits{
    SECONDS,
    MINUTE,
    HOUR,
    DAY
}