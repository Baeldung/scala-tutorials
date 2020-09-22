package services

import java.util.{Calendar, Date, TimeZone}

import org.mockito.MockitoSugar
import org.scalatestplus.play._
import org.scalatestplus.play.guice._
import play.api.test._

class MyServiceSpec
    extends PlaySpec
    with GuiceOneAppPerTest
    with Injecting
    with MockitoSugar {

  val signUpDate: Date = {
    val c: Calendar = Calendar.getInstance()
    c.set(2020, 8, 22, 10, 10, 6)
    c.set(Calendar.MILLISECOND, 0)
    c.getTime
  }
  "MyService" should {

    "Return the correct PlayerInfo information using individual gets" in {
      val myService = inject[MyService]

      val playerInfo = PlayerInfo(
        "Player 1",
        "player1@hiddencodex.com",
        18,
        signUpDate,
        None
      )
      myService.getPlayerInfoVersion1 mustBe playerInfo
    }
  }

  "Return the correct PlayerInfo information using ConfigLoader" in {
    val myService = inject[MyService]

    val playerInfo = PlayerInfo(
      "Player 1",
      "player1@hiddencodex.com",
      18,
      signUpDate,
      None
    )
    myService.getPlayerInfoVersion2 mustBe playerInfo
  }
}
