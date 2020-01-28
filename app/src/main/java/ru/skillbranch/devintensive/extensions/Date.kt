package ru.skillbranch.devintensive.extensions

import java.text.SimpleDateFormat
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

fun Date.humanizeDiff(date:Date = Date()): String {
 return date.toString()
}

enum class TimeUnits{
    SECONDS,
    MINUTE,
    HOUR,
    DAY
}