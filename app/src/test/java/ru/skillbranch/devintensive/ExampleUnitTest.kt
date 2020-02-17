package ru.skillbranch.devintensive

import org.junit.Test

import org.junit.Assert.*
import ru.skillbranch.devintensive.extensions.TimeUnits
import ru.skillbranch.devintensive.extensions.add
import ru.skillbranch.devintensive.extensions.format
import ru.skillbranch.devintensive.extensions.toUserView
import ru.skillbranch.devintensive.models.*
import ru.skillbranch.devintensive.models.User.UserBuilder
import ru.skillbranch.devintensive.utils.Utils
import java.util.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }
    @Test
    fun test_instance() {
        val user = User("1")
//        val user2 = User("2", "John", "Wick")
//        val user3 = User("3", "John", "Silverhand", null, lastVisit= Date(),isOnline=true)
//        user.printMe()
//        user2.printMe()
//        user3.printMe()
        println("$user ")
    }
    @Test
    fun test_factory() {
//        val user = User.makeUser("John Cena")
//        val user2 = User.makeUser("John Wick")
        val user = User.makeUser("John Wick")
        val user2 = user.copy(id="2", lastName="Cena")
                print("$user $user2")
    }

    @Test
    fun test_copy() {
        val user = User.makeUser("John Wick")
        var user2 = user.copy()

        if(user == user2){
            println("equals data and hash \n ${user.hashCode()}  $user \n ${user2.hashCode()} $user2")
        } else {
            println("not equals data and hash \n ${user.hashCode()}  $user \n ${user2.hashCode()} $user2")
        }
        user2 = user
        user2.lastName = "Doe"
        if(user == user2){
            println("equals data and hash \n ${user.hashCode()}  $user \n ${user2.hashCode()} $user2")
        } else {
            println("not equals data and hash \n ${user.hashCode()}  $user \n ${user2.hashCode()} $user2")
        }

    }

    @Test
    fun test_copy1() {
        val user = User.makeUser("John Wick")
        val user2 = user.copy(lastVisit = Date().add(-2, TimeUnits.SECONDS))
        val user3 = user.copy(lastName = "Cena", lastVisit = Date().add(2,TimeUnits.HOUR))
        val user4 = user.copy(lastName = "Cena", lastVisit = Date())
        print("""
        ${user.lastVisit?.format() } 
        ${user2.lastVisit?.format()}   
        ${user3.lastVisit?.format()}
        ${user4.lastVisit?.format()}           
      """.trimIndent()  )
    }

    @Test
    fun data_mapping() {
        val user = User.makeUser("John Wick")
        val userView = user.toUserView()
        userView.printMe()
    }

    @Test
    fun test_abstract_factory() {
        val user = UserBuilder("Michel Mikheev")
            .setlastVisit(Date().add(-32, TimeUnits.DAY))
            .build()
        val txtMessage = BaseMessage.makeMessage(user, Chat("0"),payload = "any text message", type= "text")
        val imgMessage = BaseMessage.makeMessage(user, Chat("0"),payload = "any image url", type= "image")
           println(txtMessage.formatMessage())
           println(imgMessage.formatMessage())
           println(Utils.toInitials("Michel Mikheev"))
           println(Utils.transliteration("Michel Ivanovich Mikheev"))
    }

    @Test
    fun test_period() {
        var user = UserBuilder("Michel Mikheev")
            .setlastVisit(Date().add(0, TimeUnits.DAY))
            .build()
        var imgMessage: BaseMessage
        for (i in 0 downTo -150){
            user =  UserBuilder("Michel Mikheev")
                .setlastVisit(Date().add(i, TimeUnits.DAY))
                .build()
            imgMessage = BaseMessage.makeMessage(user, Chat("0"),payload = "any image url", type= "image")
            println(imgMessage.formatMessage())
        }
    }
}
