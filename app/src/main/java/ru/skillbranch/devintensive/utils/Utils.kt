package ru.skillbranch.devintensive.utils

object Utils {
    fun parseFullName(fullName:String?): Pair<String?, String?> {
        val parts : List<String>? = fullName?.split(" ")
        val firstName = parts?.getOrNull(0)
        val lastName =  parts?.getOrNull(1)
        return firstName to lastName
    }

    fun transliteration(payload: String): String {
        val myRegEx: Regex = """[^a-zA-Z]*""".toRegex()
        val newString:String = myRegEx.replace(payload, "")
      return newString
    }

    fun toInitials(fullName: String?): String? {
        val (firstName, lastName) = parseFullName(fullName)
        val  firstIn: Char? = firstName?.get(0)
        val  lastIn: Char? = lastName?.get(0)
        return "$firstIn.$lastIn."
    }

    fun truncate(oldString:String?, countSimbols:Int):String?{
        if(oldString.isNullOrEmpty())
            return null
        if(oldString.length <= countSimbols)

            return oldString
        else
            return  oldString.substring(0,countSimbols) +"..."
    }

    fun stripHtml(oldString:String):String{
        val myRegEx: Regex = """<[^>]*>""".toRegex()
        val newString:String = myRegEx.replace(oldString, "\n ")
        return newString
    }
}