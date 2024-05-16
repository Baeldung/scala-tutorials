package com.baeldung.arrival.service

import com.baeldung.arrival.db.manager.DbManager
import com.baeldung.arrival.db.repository.{Arrival, ArrivalRepository}
import com.baeldung.arrival.modules.ServiceModule
import org.mockito.ArgumentMatchers
import org.mockito.Mockito.when
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.wordspec.AnyWordSpec
import org.scalatestplus.mockito.MockitoSugar
import org.scalatestplus.play.guice.GuiceOneAppPerTest
import play.api.inject.guice.GuiceApplicationBuilder
import play.api.{Application, Configuration, inject}
import slick.dbio.{DBIO, SuccessAction}

import scala.concurrent.Future

class ArrivalServiceMocksUnitTest
  extends AnyWordSpec
  with GuiceOneAppPerTest
  with ScalaFutures
  with MockitoSugar {

  private val mockDbManager: DbManager = {
    val mocked = mock[DbManager]
    when(mocked.execute(ArgumentMatchers.any[DBIO[Seq[Arrival]]]))
      .thenReturn(
        Future.successful(Seq(Arrival(33L, "Athens", "Gatwick", "DC10")))
      )
    mocked
  }

  private val mockArrivalRepository: ArrivalRepository = {
    val mocked = mock[ArrivalRepository]
    when(mocked.getArrivals)
      .thenReturn(SuccessAction(Seq.empty[Arrival]))
    mocked
  }

  override def fakeApplication(): Application = {
    GuiceApplicationBuilder(
      modules = Seq(new ServiceModule)
    )
      .loadConfig(env =>
        Configuration
          .load(env, Map("config.resource" -> "application.test.conf"))
      )
      .configure("play.http.router" -> "play.api.routing.Router")
      .bindings(inject.bind[DbManager].toInstance(mockDbManager))
      .bindings(
        inject.bind[ArrivalRepository].toInstance(mockArrivalRepository)
      )
      .build()
  }

  "ArrivalService#getArrivals" should {
    "use the mocked DbManager and ArrivalRepository without a connection to a database" in {
      val arrivalsF = app.injector.instanceOf[ArrivalService].getArrivals()
      whenReady(arrivalsF)(arrivals => {
        assert(arrivals.length === 1)
      })
    }
  }

}
