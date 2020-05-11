package com.baeldung.scala.futures

import java.util.concurrent.TimeUnit

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}
import scala.util.{Failure, Success}

object Futures extends App {
  def getToken(): String = "secretToken"
  val tokenF: Future[String] = Future(getToken())

  val numberF: Future[Int] = Future.successful(5)
  val failedF: Future[Int] = Future.failed(new IllegalArgumentException("Boom!"))

  val token: String = Await.result(tokenF, Duration(10, TimeUnit.SECONDS))

  numberF.onComplete {
    case Failure(exception) => println("Failed with: " + exception.getMessage)
    case Success(number)    => println("Succeed with: " + number)
  }
  numberF.foreach(number => println("Succeed with: " + number))

  val failureF: Future[Throwable] = failedF.failed
  val withFallback: Future[Int] = failedF.fallbackTo(numberF)
  val recovered: Future[Int] = failedF.recover {
    case _: IllegalArgumentException => 0
  }
  val recoveredWith: Future[Int] = failedF.recoverWith {
    case _: IllegalAccessException => numberF
  }

  case class User(id: Int, name: String)

  def decodeToken(token: String): User = User(1, "John")
  val userF: Future[User] = tokenF.map(decodeToken)

  case class Coffee(id: Int, name: String, price: Double)

  def getFavouriteCoffees(userId: Int): Future[List[Coffee]] =
    Future.successful(
      List(
        Coffee(1, "Rwanda Kageyo Lot", 55.0),
        Coffee(2, "Kenya Rungeto Kii", 65.0),
      )
    )

  val userFavouriteCoffeesF: Future[List[Coffee]] = userF.flatMap(user => getFavouriteCoffees(user.id))

  def bestCoffeeFor(brewingMethod: String): Future[Coffee] =
    Future.successful(
      Coffee(3, "Ethiopia Dimtu Tero", 56.0)
    )

  val pairOfCoffees: Future[(Coffee, List[Coffee])] = bestCoffeeFor("drip").zip(userFavouriteCoffeesF)
  val listOfCoffees: Future[List[Coffee]] = bestCoffeeFor("aeropress").zipWith(userFavouriteCoffeesF)(_ :: _)

  val favouriteBrewingMethods: List[String] = List("drip", "aeropress", "chemex")

  val traversedCoffees: Future[List[Coffee]] = Future.traverse(favouriteBrewingMethods)(bestCoffeeFor)

  val coffees: List[Future[Coffee]] = favouriteBrewingMethods.map(bestCoffeeFor)
  val sequencedCoffees: Future[List[Coffee]] = Future.sequence(coffees)
}
