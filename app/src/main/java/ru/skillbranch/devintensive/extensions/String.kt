package ru.skillbranch.devintensive.extensions


    fun String.truncate(countSimbols: Int = 16): String?{
//        if(this.isNullOrEmpty())
//            return null
        if(this.length <= countSimbols)

            return this
        else
            return  this.substring(0,countSimbols) +"..."
    }
