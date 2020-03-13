package ru.skillbranch.devintensive.extensions


    fun String.truncate(countSimbols: Int = 16): String?{
        if(this.length <= countSimbols)

            return this
        else
            return  this.substring(0,countSimbols) +"..."
    }

    fun String.stripHtml(): String {
        val myRegEx: Regex = """<[^>]*>""".toRegex()
        val listString: List<String> = myRegEx.replace(this, " ").split(" ")
        val newString =  StringBuilder()
        listString.forEach {st -> if (!st.isBlank()) newString.append(st).append(" ")}
        return newString.toString().trim()
    }
