package com.baeldung.scala.future

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.{Failure, Success, Try}

object FutureRecovery {

  sealed trait Weather
  object Weather {
    def apply(s: String): Weather = s match {
      case "Sunny" => Sunny
      case "Cloudy" => Cloudy
      case "Rainy" => Rainy
      case "Windy" => Windy
      case "Snowy" => Snowy
      case "Foggy" => Foggy
    }
  }
  case object Sunny extends Weather
  case object Cloudy extends Weather
  case object Rainy extends Weather
  case object Windy extends Weather
  case object Snowy extends Weather
  case object Foggy extends Weather

  class WeatherForecastService(val http: HttpClient) {

    var lastWeatherValue: Weather = Sunny
    def forecast(date: String): Future[Weather] =
      http.get(s"http://weather.now/rome?when=$date")
        .transform {
          case Success(result) =>
            val retrieved = Weather(result)
            lastWeatherValue = retrieved
            Try(retrieved)
          case Failure(exception) =>
            println(s"Something went wrong, ${exception.getMessage}")
            Try(lastWeatherValue)
        }

    def forecastUsingMapAndRecover(date: String): Future[Weather] =
      http.get(s"http://weather.now/rome?when=$date")
        .map { result =>
          val retrieved = Weather(result)
          lastWeatherValue = retrieved
          retrieved
        }
        .recover {
          case e: Exception =>
            println(s"Something went wrong, ${e.getMessage}")
            lastWeatherValue
        }

    def forecast(date: String, fallbackUrl: String): Future[Weather] =
      http.get(s"http://weather.now/rome?when=$date")
        .transformWith {
          case Success(result) =>
            val retrieved = Weather(result)
            lastWeatherValue = retrieved
            Future(retrieved)
          case Failure(exception) =>
            println(s"Something went wrong, ${exception.getMessage}")
            http.get(fallbackUrl).map(Weather(_))
        }

    def forecastUsingFlatMapAndRecoverWith(date: String, fallbackUrl: String): Future[Weather] =
      http.get(s"http://weather.now/rome?when=$date")
        .flatMap { result =>
          val retrieved = Weather(result)
          lastWeatherValue = retrieved
          Future(retrieved)
        }
        .recoverWith {
          case e: Exception =>
            println(s"Something went wrong, ${e.getMessage}")
            http.get(fallbackUrl).map(Weather(_))
        }
  }

  class HttpClient {
    def get(url: String): Future[String] =
      if (url.contains("2020-10-18"))
        Future("Sunny")
      else if (url.contains("2020-10-19"))
        Future("Windy")
      else {
        Future {
          throw new RuntimeException
        }
      }
  }
}
