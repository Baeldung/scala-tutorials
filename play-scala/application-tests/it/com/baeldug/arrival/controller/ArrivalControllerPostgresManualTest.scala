package com.baeldung.arrival.controller

import com.baeldung.arrival.applicationfactory.PostgresApplicationFactory
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.time.{Millis, Seconds, Span}
import org.scalatest.wordspec.AnyWordSpec
import org.scalatestplus.play.WsScalaTestClient
import org.scalatestplus.play.guice.GuiceOneServerPerTest
import play.api.libs.json.JsArray
import play.api.libs.ws.{WSClient, WSResponse}

import scala.concurrent.Future

class ArrivalControllerPostgresManualTest
  extends AnyWordSpec
  with WsScalaTestClient
  with GuiceOneServerPerTest
  with ScalaFutures
  with PostgresApplicationFactory {

  private implicit def wsClient = app.injector.instanceOf[WSClient]

  "ArrivalController#index" should {
    "return arrivals using postgres" in {
      val controllerResponseF: Future[WSResponse] = wsCall(
        com.baeldung.arrival.controller.routes.ArrivalController.index()
      ).get()
      whenReady(controllerResponseF)(controllerResponse => {
        val arrivals = controllerResponse.json.as[JsArray].value
        assert(arrivals.length === 5)
      })
    }
  }

  override implicit def patienceConfig: PatienceConfig = PatienceConfig(
    timeout = scaled(Span(5, Seconds)),
    interval = scaled(Span(200, Millis))
  )
}
