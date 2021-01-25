package errors

import akka.http.scaladsl.model.StatusCodes
import org.scalatest.concurrent.Eventually
import org.scalatestplus.play.PlaySpec
import org.scalatestplus.play.guice.GuiceOneAppPerTest
import play.api.test.Helpers._
import play.api.test._

class CustomErrorHandlerSpec extends PlaySpec with GuiceOneAppPerTest with Injecting with Eventually {
  "CustomErrorHandler" should {
    "redirect to the home page when a page has not been found" in {
      //given
      val objectUnderTest = new CustomErrorHandler()
      val request = FakeRequest(GET, "/fake")
      val statusCode = StatusCodes.NotFound
      val message = ""

      //when
      val responseFuture = objectUnderTest.onClientError(request, statusCode.intValue, message)

      //then
      eventually {
        status(responseFuture) mustBe StatusCodes.SeeOther.intValue
      }
    }
  }
}
