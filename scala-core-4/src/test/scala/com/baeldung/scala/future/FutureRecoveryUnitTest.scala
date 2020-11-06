package com.baeldung.scala.future

import com.baeldung.scala.future.FutureRecovery.{Sunny, Windy}
import org.scalatest.AsyncFlatSpec

class FutureRecoveryUnitTest extends AsyncFlatSpec {

  private val httpClient = new FutureRecovery.HttpClient()
  private val forecastService = new FutureRecovery.WeatherForecastService(httpClient)

  "A forecast" should "return the current forecast" in {
    forecastService.forecast("2020-10-18") map { forecast =>
      assert(forecast == Sunny)
    }
  }

  it should "return the previous forecast in case of error" in {
    forecastService.forecast("2020-10-19") andThen {
      case _ => forecastService.forecast("2020-10-20")
    } map { forecast =>
      assert(forecast == Windy)
    }
  }

  "A forecast with fallback" should "return the current forecast" in {
    forecastService.forecast("2020-10-18", "http://weather.com") map { forecast =>
      assert(forecast == Sunny)
    }
  }

  it should "return the forecast given by the fallback service in case of error" in {
    forecastService.forecast(
      "2020-10-20",
      "http://weather.com/2020-10-18") map { forecast =>
      assert(forecast == Sunny)
    }
  }

  "A forecast made in Scala < 2.12" should "return the current forecast" in {
    forecastService.forecastUsingMapAndRecover("2020-10-18") map { forecast =>
      assert(forecast == Sunny)
    }
  }

  it should "return the previous forecast in case of error" in {
    forecastService.forecastUsingMapAndRecover("2020-10-19") andThen {
      case _ => forecastService.forecastUsingMapAndRecover("2020-10-20")
    } map { forecast =>
      assert(forecast == Windy)
    }
  }

  "A forecast with fallback made in Scala < 2.12" should "return the current forecast" in {
    forecastService.forecastUsingFlatMapAndRecoverWith(
      "2020-10-18",
      "http://weather.com") map { forecast =>
      assert(forecast == Sunny)
    }
  }

  it should "return the forecast given by the fallback service in case of error" in {
    forecastService.forecastUsingFlatMapAndRecoverWith(
      "2020-10-20",
      "http://weather.com/2020-10-18") map { forecast =>
      assert(forecast == Sunny)
    }
  }
}
