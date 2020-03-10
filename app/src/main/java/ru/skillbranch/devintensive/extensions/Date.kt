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
    val newDate = Date()
    val delta: Long
    if (date != null)
        delta = date.time - newDate.time
    else
        return null
    return unit?.plural(delta)
}

fun Date.humanizeDiff(): String? {
   return humanizeDiff(this)
}

enum class TimeUnits {
    SECOND,
    MINUTE,
    HOUR,
    DAY;

    fun plural(number: Long): String {
        val num: Int
        val sign: Int
        val pre: String
        val po: String
        var dayString: String

        if (number < 0) {
            sign = -1
            pre = "  "
            po = " назад"

        } else {
            sign = 1
            pre = "через "
            po = " "
        }
        when (this) {
            SECOND -> {
                num = sign * ((number / SECONDS).toInt())
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
                num = sign * (number / MINUTES).toInt()
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
                num = sign * (number / HOURS).toInt()
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
                num = sign * (number / DAYS).toInt()
                if (num > 366) {
                    if (sign > 0)
                        return "больше чем через год "
                    else
                        return "больше года назад "
                }
                when (num) {
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
        dayString = pre + num + dayString + po
        return dayString
    }
}

//fun Date.plural(date: Date?): String {
//    var period: Int = Date().humanizeDiff(date)
//    var dayString: String = " дней назад"
//    when (period) {
//        0 -> return " сегодня"
//        1 -> return " вчера"
//        else -> {
//            if (period < 0) return " - ошибка даты "
//            else if (period / 10 != 1) {
//                dayString = when (period % 10) {
//                    1 -> " день назад"
//                    2, 3, 4 -> " дня назад"
//                    else -> " дней назад"
//                }
//            } else dayString = " дней назад"
//        }
//    }
//    return (period.toString() + dayString)
//}

