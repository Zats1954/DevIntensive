package ru.skillbranch.devintensive


import org.junit.Test
import org.junit.Assert.*
import ru.skillbranch.devintensive.extensions.*
import ru.skillbranch.devintensive.models.*
import ru.skillbranch.devintensive.models.User.UserBuilder
import ru.skillbranch.devintensive.utils.Utils
import java.util.Date


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
        val user2 = user.copy(lastVisit = Date().add(-2, TimeUnits.SECOND))
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
            .lastVisit(Date().add(-32, TimeUnits.DAY))
            .build()
        val txtMessage = BaseMessage.makeMessage(user, Chat("0"),payload = "any text message", type= "text")
        val imgMessage = BaseMessage.makeMessage(user, Chat("0"),payload = "any image url", type= "image")
           println(Utils.parseFullName(" "))
           println(txtMessage.formatMessage())
           println(imgMessage.formatMessage())
           println(Utils.toInitials("michel mikheev"))
           println(Utils.transliteration("Michel325 Иванович+- Шукарь"))
           println(Utils.transliteration("Michel325 Иванович+- Шукарь","_"))
           println(("Michel Ivanovich Mikheev".truncate()))
           println(Utils.stripHtml("<html> <body style=\"width=24px\"> <table> <tr><td>Kitchen</td><td>Sleeproom</td></tr><table></body></html>"))
    }

    @Test
    fun test_date() {
        println(Date().add(-2, TimeUnits.HOUR).humanizeDiff()) //2 часа назад
        println(Date().add(-5, TimeUnits.DAY).humanizeDiff()  )  //5 дней назад)
        println(Date().add(2, TimeUnits.MINUTE).humanizeDiff())     //через 2 минуты)
        println(Date().add(7, TimeUnits.DAY).humanizeDiff()   )   //через 7 дней)
        println(Date().add(-400, TimeUnits.DAY).humanizeDiff())     //более года назад)
        println(Date().add(400, TimeUnits.DAY).humanizeDiff() )     //более чем через год)
    }
}
