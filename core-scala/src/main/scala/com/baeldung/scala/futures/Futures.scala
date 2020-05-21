package com.baeldung.scala.futures

import java.util.concurrent._

import scala.concurrent.duration.Duration
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

    val numberF: Future[Int] = Future.successful(5)
    val failedF: Future[Int] =
      Future.failed(new IllegalArgumentException("Boom!"))

    val magicNumber: Int =
      Await.result(generatedMagicNumberF, Duration(5, TimeUnit.SECONDS))

    def printResult[A](result: Try[A]): Unit = result match {
      case Failure(exception) => println("Failed with: " + exception.getMessage)
      case Success(number)    => println("Succeed with: " + number)
    }

    numberF.onComplete(printResult)

    def printSucceedResult[A](result: A): Unit =
      println("Succeed with: " + result)

    numberF.foreach(printSucceedResult)

    val failureF: Future[Throwable] = failedF.failed
    val recovered: Future[Int] = failedF.recover {
      case _: IllegalArgumentException => 0
    }
    val recoveredWith: Future[Int] = failedF.recoverWith {
      case _: IllegalArgumentException => numberF
    }

    val magicNumberF: Future[Int] = repository
      .readMagicNumber()
      .fallbackTo(backup.readMagicNumberFromLatestBackup())

    def increment(number: Int): Int = number + 1

    val nextMagicNumber: Future[Int] = magicNumberF.map(increment)
    val updatedMagicNumber: Future[Boolean] =
      nextMagicNumber.flatMap(repository.updateMagicNumber)

    val pairOfMagicNumbers: Future[(Int, Int)] =
      repository.readMagicNumber().zip(backup.readMagicNumberFromLatestBackup())

    def areEqual(x: Int, y: Int): Boolean = x == y

    val areMagicNumbersEqual: Future[Boolean] =
      repository
        .readMagicNumber()
        .zipWith(backup.readMagicNumberFromLatestBackup())(areEqual)

    val magicNumbers: List[Int] = List(1, 2, 3, 4)
    val published: Future[List[Boolean]] =
      Future.traverse(magicNumbers)(publisher.publishMagicNumber)
  }

}
