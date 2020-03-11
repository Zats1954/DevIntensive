package ru.skillbranch.devintensive.utils

import java.util.*


object Utils {
    var trans =
        mutableMapOf(
            'а' to "a", 'б' to "b", 'в' to "v", 'г' to "g",
            'д' to "d", 'е' to "e", 'ё' to "e", 'ж' to "zh",
            'з' to "z", 'и' to "i", 'й' to "i", 'к' to "k",
            'л' to "l", 'м' to "m", 'н' to "n", 'о' to "o",
            'п' to "p", 'р' to "r", 'с' to "s", 'т' to "t",
            'у' to "u", 'ф' to "f", 'х' to "h", 'ц' to "c",
            'ч' to "ch", 'ш' to "sh", 'щ' to "sh'", 'ъ' to "",
            'ы' to "i", 'ь' to "", 'э' to "e", 'ю' to "yu", 'я' to "ya"
        )

    fun parseFullName(fullName: String?): Pair<String?, String?> {
        val parts: List<String>? = fullName?.split(" ")
        val firstName = parts?.getOrNull(0)
        if (firstName.isNullOrEmpty())
            return null to null
        val lastName = parts.getOrNull(1)
        return firstName to lastName
    }

    fun transliteration(payload: String): String {
        val newString: StringBuilder = java.lang.StringBuilder()
        for (c in payload) {
            if (c.isUpperCase()) {
                if (trans.containsKey(c.toLowerCase()))
                    newString.append(trans.get(c.toLowerCase())?.toUpperCase(Locale.ROOT))
                else
                    newString.append(c)
            } else
                if (trans.containsKey(c))
                    newString.append(trans.get(c))
                else
                    newString.append(c)
        }
        return newString.toString()
    }

    fun transliteration(payload: String, symbol: String): String {
        trans[' '] = symbol
        return transliteration(payload)
    }

//    fun toInitials(fullName: String?): String? {
//        val (firstName, lastName) = parseFullName(fullName)
//        val firstIn: Char? = firstName?.toUpperCase(Locale.ROOT)?.get(0)
//        val lastIn: Char? = lastName?.toUpperCase(Locale.ROOT)?.get(0)
//        return "$firstIn.$lastIn."
//    }

    fun toInitials(firstName: String?, lastName: String?): String? {
        val firstIn: Char? = firstName?.toUpperCase(Locale.ROOT)?.get(0)
        val lastIn: Char? = lastName?.toUpperCase(Locale.ROOT)?.get(0)
        if (firstIn == null || firstIn == ' ') {
            if (lastIn == null || lastIn == ' ')
                return null
            else
                return "$lastIn"
        } else {
            if (lastIn == null || lastIn == ' ')
                return "$firstIn"
            else
                return "$firstIn$lastIn"
        }
    }

    fun stripHtml(oldString: String): String {
        val myRegEx: Regex = """<[^>]*>""".toRegex()
        val newString: String = myRegEx.replace(oldString, "\n ")
        return newString
    }


}