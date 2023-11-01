package com.baeldung.arrival.controller

import com.baeldung.arrival.applicationfactory.H2ApplicationFactory
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.time.{Millis, Seconds, Span}
import org.scalatest.wordspec.AnyWordSpec
import org.scalatestplus.play.WsScalaTestClient
import org.scalatestplus.play.guice.GuiceOneServerPerTest
import play.api.libs.json.JsArray
import play.api.libs.ws.{WSClient, WSResponse}

import scala.concurrent.Future

class ArrivalControllerH2Test
  extends AnyWordSpec
  with WsScalaTestClient
  with GuiceOneServerPerTest
  with ScalaFutures
  with H2ApplicationFactory {

  private implicit def wsClient: WSClient = app.injector.instanceOf[WSClient]

  "ArrivalController#index" should {
    "return arrivals using h2" in {
      val controllerResponseF: Future[WSResponse] = wsCall(
        com.baeldung.arrival.controller.routes.ArrivalController.index()
      ).get()
      whenReady(controllerResponseF)(controllerResponse => {
        val arrivals = controllerResponse.json.as[JsArray].value
        assert(arrivals.length === 6)
      })
    }
  }

  override implicit def patienceConfig: PatienceConfig = PatienceConfig(
    timeout = scaled(Span(10, Seconds)),
    interval = scaled(Span(300, Millis))
  )
}
