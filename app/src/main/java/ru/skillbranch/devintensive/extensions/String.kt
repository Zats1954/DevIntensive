package ru.skillbranch.devintensive.extensions


    fun String.truncate(countSimbols: Int = 16): String?{
        if(this.length <= countSimbols)

            return this
        else
            return  this.substring(0,countSimbols) +"..."
    }

    fun String.stripHtml(): String {
        val myRegEx: Regex = """<[^>]*>""".toRegex()
        val newString: String = myRegEx.replace(this, " ").trim()
        return newString
    }
