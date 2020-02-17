package ru.skillbranch.devintensive.models

import ru.skillbranch.devintensive.utils.Utils
import java.util.*

data class User (
    val id:String ,
    var firstName:String?,
    var lastName:String?,
    var avatar:String?,
    var rating:Int=0,
    var respect:Int=0,
    var lastVisit:Date?=null,
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
        fun    setAvatar(avatar:String) = apply   {user.avatar = avatar}
        fun    setRating(rating:Int)      = apply {user.rating = rating}
        fun   setrespect(respect:Int)     = apply {user.respect = respect}
        fun setlastVisit(lastVisit:Date)  = apply {user.lastVisit = lastVisit}
        fun  setisOnline(isOnline:Boolean)= apply {user.isOnline = isOnline}
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