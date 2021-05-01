package controllers

import models.PriceFormatter
import org.scalatestplus.play.PlaySpec
import org.scalatestplus.play.guice.GuiceOneAppPerTest
import play.api.test.Helpers.{GET, contentAsString, stubControllerComponents, _}
import play.api.test.{FakeRequest, Injecting}

class MenuControllerSpec extends PlaySpec with GuiceOneAppPerTest with Injecting {
    "MenuController should return the menu positions" should {
        "return the price in the right format" in {
            val priceFormatter = new PriceFormatter()
            val template = new views.html.Baeldung.menu(priceFormatter)

            val controller = new MenuController(template, stubControllerComponents())

            val result = controller.availableProducts().apply(
                FakeRequest(GET, "/menu")
            )

            contentAsString(result) must include("coffee 8,99 €")
        }
    }
}
