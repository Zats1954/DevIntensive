package ru.skillbranch.devintensive.utils

object Utils {
    fun parseFullName(fullName:String?): Pair<String?, String?> {
        val parts : List<String>? = fullName?.split(" ")
        val firstName = parts?.getOrNull(0)
        val lastName =  parts?.getOrNull(1)
        return firstName to lastName
    }

    fun transliteration(payload: String, divider:String = " "): String {
        var parts : List<String>? = payload?.split(divider)
        var newString: String =""
        parts?.forEach{newString += it }
      return newString
    }

    fun toInitials(fullName: String?): String? {
        val (firstName, lastName) = parseFullName(fullName)
        val  firstIn: Char? = firstName?.get(0)
        val  lastIn: Char? = lastName?.get(0)
        return "$firstIn.$lastIn."
    }

}