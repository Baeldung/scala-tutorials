package services

import org.mockito.MockitoSugar
import org.scalatestplus.play._
import org.scalatestplus.play.guice._
import play.api.test.Helpers._
import play.api.test._

class MyServiceSpec
    extends PlaySpec
    with GuiceOneAppPerTest
    with Injecting
    with MockitoSugar {
  "MyService" should {

    "Return the correct PlayerInfo information" in {
      val myService = inject[MyService]

      val playerInfo = PlayerInfo(
        "Player 1",
        "player1@hiddencodex.com",
        18,
        None
      )
      myService.getPlayerInfoVersion1 mustBe playerInfo
      myService.getPlayerInfoVersion2 mustBe playerInfo
    }
  }
}
