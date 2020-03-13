package ru.skillbranch.devintensive.extensions

import java.text.SimpleDateFormat
import java.util.*


const val SECONDS = 1000L
const val MINUTES = 60 * SECONDS
const val HOURS = 60 * MINUTES
const val DAYS = 24 * HOURS
var unit: TimeUnits? = null

fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy"): String {
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.add(value: Int, units: TimeUnits): Date {
    var time = this.time
    unit = units
    time += when (units) {
        TimeUnits.SECOND -> value * SECONDS
        TimeUnits.MINUTE -> value * MINUTES
        TimeUnits.HOUR -> value * HOURS
        TimeUnits.DAY -> value * DAYS
    }
    this.time = time
    return this
}

fun Date.humanizeDiff(date: Date?): String? {
    val pre: String
    val po: String
    val newDate = Date()
    var num = 0
    val delta: Long
    if (date != null) {
        delta = date.time - newDate.time
        when (unit) {
            TimeUnits.SECOND -> {
                num =(delta / SECONDS).toInt() }
            TimeUnits.MINUTE -> {
                num =(delta / MINUTES).toInt()}
            TimeUnits.HOUR ->{
                num = (delta/ HOURS).toInt()
            }
            TimeUnits.DAY -> {
                num = (delta / DAYS).toInt()
                if(num >366)
                    return unit?.plural(num)
                }
    }
    }
    else
        return null
    if (delta < 0) {
        pre = "  "
        po = " назад"
    } else {
        pre = "через "
        po = " "
    }
    return pre + unit?.plural(num) + po
}

fun Date.humanizeDiff(): String? {
   return humanizeDiff(this)
}

enum class TimeUnits {
    SECOND,
    MINUTE,
    HOUR,
    DAY;

    fun plural(number: Int): String {
        val num: Int
        val dayString: String
        println("number " + number)
        if (number < 0) {
            num = -number
        } else {
            num = number
        }
        when (this) {
            SECOND -> {
                when (num) {
                    0 -> return " сейчас"
                    else -> {
                        if (num / 10 != 1) {
                            dayString = when (num % 10) {
                                1 -> " секунду "
                                2, 3, 4 -> " секунды "
                                else -> " секунд "
                            }
                        } else dayString = " секунд "
                    }
                }
            }
            MINUTE -> {
                when (num) {
                    0 -> return " сейчас"
                    else -> {
                        if (num / 10 != 1) {
                            dayString = when (num % 10) {
                                1 -> " минуту "
                                2, 3, 4 -> " минуты "
                                else -> " минут "
                            }
                        } else dayString = " минут "
                    }
                }
            }
            HOUR -> {
                when (num) {
                    0 -> return " сейчас"
                    else -> {
                        if (num / 10 != 1) {
                            dayString = when (num % 10) {
                                1 -> " час "
                                2, 3, 4 -> " часа "
                                else -> " часов "
                            }
                        } else dayString = " часов "
                    }
                }
            }
            DAY -> {
                if (num > 366) {
                    if (number > 0)
                        return "больше чем через год "
                    else
                        return "больше года "
                }
                when (number) {
                    0 -> return " сегодня"
                    else -> {
                        if (num / 10 != 1) {
                            dayString = when (num % 10) {
                                1 -> " день "
                                2, 3, 4 -> " дня "
                                else -> " дней "
                            }
                        } else dayString = " дней "
                    }
                }

            }
        }
        return num.toString() + dayString
    }
}

