package ru.skillbranch.devintensive.models

import ru.skillbranch.devintensive.extensions.TimeUnits
import ru.skillbranch.devintensive.extensions.add
import ru.skillbranch.devintensive.extensions.humanizeDiff
import ru.skillbranch.devintensive.extensions.plural
import java.util.*

class ImageMessage (
    id:String,
    from:User?,
    chat:Chat,
    isIncoming:Boolean = false,
    date: Date = Date(),
    var image:String?
):BaseMessage (id, from, chat, isIncoming, date){
    override fun formatMessage():  String = "id:$id ${from?.firstName}" +
            " ${if(isIncoming) "получил" else "отправил"} изображение \"$image\" " +
             "Последнее посещение было "  + Date().plural(from?.lastVisit)
}