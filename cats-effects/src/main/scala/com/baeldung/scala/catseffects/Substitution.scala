package com.baeldung.scala.catseffects

import scala.concurrent.{ExecutionContext, ExecutionContextExecutor, Future}

object Substitution {
  def effectful(): Unit = {
    val tuple = (println("Launch missiles"), println("Launch missiles"))
    println("--------------")
    val print: Unit = println("Launch missiles")
    (print, print)
  }

  def effectfulWithFuture(): Unit = {
    implicit val ec: ExecutionContextExecutor = ExecutionContext.global
    Future(println("Launch missiles")).map(_ =>
      Future(println("Launch missiles"))
    )
  }

  def effectfulWithFutureRefactored(): Unit = {
    implicit val ec: ExecutionContextExecutor = ExecutionContext.global
    val lauch = Future(println("Launch missiles"))
    lauch.map(_ => lauch)
  }
}
