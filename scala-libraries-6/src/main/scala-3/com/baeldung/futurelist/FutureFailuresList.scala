package com.baeldung.futurelist

import scala.concurrent.{ExecutionContext, Future}
import scala.util.{Success, Failure, Try}
import scala.concurrent.ExecutionContext.Implicits.global

object FutureFailuresList {

  private def usingTransform(futures: List[Future[String]]): Future[List[Try[String]]] = {
    Future.sequence(futures.map(f => f.transform(Success(_))))
  }

  def getSuccessfulUsingTransform(futures: List[Future[String]]): Future[List[String]] = {
    val futuresSeq = usingTransform(futures)
    futuresSeq.map(f => f.collect{ case Success(str) => str })
  }

  def getFailuresUsingTransform(futures: List[Future[String]]): Future[List[Throwable]] = {
    val futuresSeq = usingTransform(futures)
    futuresSeq.map(f => f.collect { case Failure(ex) => ex })
  }

  private def usingRecover(futures: List[Future[String]]): Seq[Future[Try[String]]] = {
    futures.map( f => {
      f.map(Success(_)).recover{ case x => Failure(x)}
    })
  }

  def getSuccessfulUsingRecover(futures: List[Future[String]]): Future[Seq[String]] = {
    val futuresSeq = Future.sequence(usingRecover(futures))
    futuresSeq.map(f => f.filter {_.isSuccess}.collect{ case Success(str) => str })
  }

  def getFailuresUsingRecover(futures: List[Future[String]]): Future[Seq[Throwable]] = {
    val futuresSeq = Future.sequence(usingRecover(futures))
    futuresSeq.map(f => f.filter {_.isFailure}.collect { case Failure(ex) => ex })
  }

}
