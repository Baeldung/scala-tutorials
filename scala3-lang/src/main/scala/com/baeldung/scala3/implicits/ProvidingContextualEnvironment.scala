package com.baeldung.scala3.implicits

import scala.concurrent.duration.*
import scala.concurrent.{Await, ExecutionContext, Future}

object ProvidingContextualEnvironment {

  def square(i: Int)(using ec: ExecutionContext): Future[Int] = 
    Future(i * i)

}
