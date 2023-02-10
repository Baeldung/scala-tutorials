package com.baeldung.scala.futures

import java.util.concurrent._
import scala.concurrent.duration.{Duration, FiniteDuration}
import scala.concurrent.{Await, ExecutionContext, Future}
import scala.util.{Failure, Success, Try}

object Futures extends App {

  trait ExecutionContexts {
    val forkJoinPool: ExecutorService = new ForkJoinPool(4)
    implicit val forkJoinExecutionContext: ExecutionContext =
      ExecutionContext.fromExecutorService(forkJoinPool)

    val singleThread: Executor = Executors.newSingleThreadExecutor()
    implicit val singleThreadExecutionContext: ExecutionContext =
      ExecutionContext.fromExecutor(singleThread)

    implicit val globalExecutionContext: ExecutionContext =
      ExecutionContext.global
  }

  trait DatabaseRepository {
    def readMagicNumber(): Future[Int]

    def updateMagicNumber(number: Int): Future[Boolean]
  }

  trait FileBackup {
    def readMagicNumberFromLatestBackup(): Future[Int]
  }

  trait Publisher {
    def publishMagicNumber(number: Int): Future[Boolean]
  }

  trait MagicNumberService {
    val repository: DatabaseRepository
    val backup: FileBackup
    val publisher: Publisher

    import scala.concurrent.ExecutionContext.Implicits.global

    def generateMagicNumber(): Int = {
      Thread.sleep(3000L)
      23
    }

    val generatedMagicNumberF: Future[Int] = Future {
      generateMagicNumber()
    }

    def multiply(multiplier: Int): Future[Int] =
      if (multiplier == 0) {
        Future.successful(0)
      } else {
        Future(multiplier * generateMagicNumber())
      }

    def divide(divider: Int): Future[Int] =
      if (divider == 0) {
        Future.failed(new IllegalArgumentException("Don't divide by zero"))
      } else {
        Future(generateMagicNumber() / divider)
      }

    def tryDivide(divider: Int): Future[Int] = Future.fromTry(Try {
      generateMagicNumber() / divider
    })

    val numberF: Future[Int] = Future.successful(5)

    val maxWaitTime: FiniteDuration = Duration(5, TimeUnit.SECONDS)
    val magicNumber: Int = Await.result(generatedMagicNumberF, maxWaitTime)

    def printResult[A](result: Try[A]): Unit = result match {
      case Failure(exception) => println("Failed with: " + exception.getMessage)
      case Success(number)    => println("Succeed with: " + number)
    }

    numberF.onComplete(printResult)

    def printSucceedResult[A](result: A): Unit =
      println("Succeed with: " + result)

    numberF.foreach(printSucceedResult)

    val failedF: Future[Int] =
      Future.failed(new IllegalArgumentException("Boom!"))
    val failureF: Future[Throwable] = failedF.failed
    val recoveredF: Future[Int] = Future(3 / 0).recover {
      case _: ArithmeticException => 0
    }
    val recoveredWithF: Future[Int] = Future(3 / 0).recoverWith {
      case _: ArithmeticException => magicNumberF
    }

    val magicNumberF: Future[Int] = repository
      .readMagicNumber()
      .fallbackTo(backup.readMagicNumberFromLatestBackup())

    def increment(number: Int): Int = number + 1

    val nextMagicNumberF: Future[Int] = magicNumberF.map(increment)
    val updatedMagicNumberF: Future[Boolean] =
      nextMagicNumberF.flatMap(repository.updateMagicNumber)

    val pairOfMagicNumbersF: Future[(Int, Int)] =
      repository.readMagicNumber().zip(backup.readMagicNumberFromLatestBackup())

    def areEqual(x: Int, y: Int): Boolean = x == y

    val areMagicNumbersEqualF: Future[Boolean] =
      repository
        .readMagicNumber()
        .zipWith(backup.readMagicNumberFromLatestBackup())(areEqual)

    val magicNumbers: List[Int] = List(1, 2, 3, 4)
    val published: Future[List[Boolean]] =
      Future.traverse(magicNumbers)(publisher.publishMagicNumber)

    val value = Future.successful(42)
    val transformed = value.transform {
      case Success(value) => Success(s"Successfully computed the $value")
      case Failure(cause) => Failure(new IllegalStateException(cause))
    }
    val overloaded = value.transform(
      value => s"Successfully computed $value",
      cause => new IllegalStateException(cause)
    )

    val f = value.transformWith {
      case Success(value) =>
        Future.successful(s"Successfully computed the $value")
      case Failure(cause) => Future.failed(new IllegalStateException(cause))
    }

    val v = Future.successful(42).andThen { case Success(v) =>
      println(s"The answer is $v")
    }

    val g = Future.successful(42).andThen { case Success(v) =>
      println(s"The answer is $v")
    } andThen {
      case Success(_) => // send HTTP request to signal success
      case Failure(_) => // send HTTP request to signal failure
    }

    g.onComplete { v =>
      println(s"The original future has returned: $v")
    }
  }
}
