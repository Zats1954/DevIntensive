package ru.skillbranch.devintensive.models

import ru.skillbranch.devintensive.utils.Utils
import java.util.*

data class User (
    var id:String ,
    var firstName:String?,
    var lastName:String?,
    var avatar:String?,
    var rating:Int=0,
    var respect:Int=0,
    var lastVisit:Date?=Date(),
    var isOnline:Boolean=false
){
    private constructor(id: String, firstName: String?, lastName: String?):
        this(
            id =id,
            firstName=firstName,
            lastName=lastName,
            avatar= null)
    constructor(id: String): this(id, firstName="John", lastName="Doe")
    init {
        println("It's Alive!!!\n ${if(lastName === "Doe") "His name is $firstName $lastName\n" 
                else "And name is $firstName $lastName!!\n"} ")
    }
    data class UserBuilder(val fullName:String?) {
        val user:User = makeUser(fullName)
        fun  id(id:String)               = apply {user.id = id}
        fun  firstName(firstName:String) = apply {user.firstName = firstName}
        fun  lastName(lastName:String)   = apply {user.lastName = lastName}
        fun  avatar(avatar:String)       = apply {user.avatar = avatar}
        fun  rating(rating:Int)          = apply {user.rating = rating}
        fun  respect(respect:Int)        = apply {user.respect = respect}
        fun  lastVisit(lastVisit:Date)   = apply {user.lastVisit = lastVisit}
        fun  isOnline(isOnline:Boolean)  = apply {user.isOnline = isOnline}
        fun build()=  user
    }
companion object Factory {
  private var lastId:Int = -1
  fun makeUser(fullName:String?): User{
      lastId++
      val (firstName, lastName) = Utils.parseFullName(fullName)
      return User(id="$lastId", firstName = firstName, lastName = lastName )
  }

}

}