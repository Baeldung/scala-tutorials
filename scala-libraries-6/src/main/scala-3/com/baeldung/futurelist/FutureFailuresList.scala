package com.baeldung.futurelist

import scala.concurrent.{ExecutionContext, Future}
import scala.util.{Success, Failure, Try}
import scala.concurrent.ExecutionContext.Implicits.global

object FutureFailuresList {

  def getSuccessful(
    futures: List[Future[String]]
  )(
    fn: List[Future[String]] => Future[List[Try[String]]]
  ): Future[List[String]] = {
    val futuresSeq = fn(futures)
    futuresSeq.map(f => f.collect { case Success(str) => str })
  }

  def getFailures(
    futures: List[Future[String]]
  )(
    fn: List[Future[String]] => Future[List[Try[String]]]
  ): Future[List[Throwable]] = {
    val futuresSeq = fn(futures)
    futuresSeq.map(f => f.collect { case Failure(ex) => ex })
  }

  def usingTransform(
    futures: List[Future[String]]
  ): Future[List[Try[String]]] = {
    Future.sequence(futures.map(f => f.transform(Success(_))))
  }

  def usingRecover(
    futures: List[Future[String]]
  ): Future[List[Try[String]]] = {
    Future.sequence(
      futures.map(f => {
        f.map(Success(_)).recover { case x => Failure(x) }
      })
    )
  }
}
