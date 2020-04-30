package com.baeldung.scala.futures

import java.util.concurrent.{Executor, ExecutorService, ForkJoinPool}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, ExecutionContext, Future}
import scala.util.{Failure, Success}

object Futures extends App {
  val executor: Executor = new ForkJoinPool(4)
  val executorService: ExecutorService = new ForkJoinPool(4)

  ExecutionContext.fromExecutor(executor)
  ExecutionContext.fromExecutorService(executorService)

  def getToken(): String = "secretToken"

  val tokenF: Future[String] = Future(getToken())
  val token: String = Await.result(tokenF, Duration.Inf)

  val numberF: Future[Int] = Future.successful(5)
  val oddNumberF: Future[Int] = numberF.filter(_ % 2 != 0) // complete with 5
  val evenNumberF: Future[Int] = numberF.filter(_ % 2 == 0) // failed with NoSuchElementException
  val failedF: Future[Int] = Future.failed(new IllegalArgumentException("Boom!"))
  val failedEvenF: Future[Int] = failedF.filter(_ % 2 == 0) // failed with IllegalArgumentException

  val negativeOddNumberF: Future[Int] = numberF.collect {
    case number if number % 2 != 0 => -number
  } // complete with -5
  val negativeEvenNumberF: Future[Int] = numberF.collect {
    case number if number % 2 == 0 => -number
  } // failed with NoSuchElementException
  val negativeFailedEvenF: Future[Int] = failedF.collect {
    case number if number % 2 == 0 => -number
  } // failed with IllegalArgumentException

  negativeOddNumberF.onComplete {
    case Failure(exception) => println("Failed with: " + exception.getMessage)
    case Success(number)    => println("Succeed with: " + number)
  }

  negativeOddNumberF.foreach(number => println("Succeed with: " + number))

  val failed: Future[Throwable] = negativeFailedEvenF.failed
  val withFallback: Future[Int] = negativeFailedEvenF.fallbackTo(negativeEvenNumberF)

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

  val favouriteCoffeesTotalPrice: Future[Double] = for {
    token <- tokenF
    favouriteCoffees <- getFavouriteCoffees(decodeToken(token).id)
  } yield favouriteCoffees.map(_.price).sum

  def bestCoffeeFor(brewingMethod: String): Future[Coffee] =
    Future.successful(
      Coffee(3, "Ethiopia Dimtu Tero", 56.0)
    )

  val tupleOfCoffees: Future[(Coffee, List[Coffee])] = bestCoffeeFor("drip").zip(userFavouriteCoffeesF)
  val listOfCoffees: Future[List[Coffee]] = bestCoffeeFor("aeropress").zipWith(userFavouriteCoffeesF)(_ :: _)

  val favouriteBrewingMethods: List[String] = List("drip", "aeropress", "chemex")
  val bestCoffeesForMe: Future[List[Coffee]] = Future.traverse(favouriteBrewingMethods)(bestCoffeeFor)

  val multipleFuturesWithCoffee: List[Future[Coffee]] = favouriteBrewingMethods.map(bestCoffeeFor)
  val singleFutureWithCoffees: Future[List[Coffee]] = Future.sequence(multipleFuturesWithCoffee)
}
