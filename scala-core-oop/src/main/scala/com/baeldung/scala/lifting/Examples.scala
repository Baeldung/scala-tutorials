package com.baeldung.scala.lifting

import scala.concurrent._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import cats.data.OptionT
import cats.implicits._
import cats._

object Examples {

  val squareRoot: PartialFunction[Double, Double] = { case x if x >= 0 => Math.sqrt(x) }

  def getSqrtRootMessagePartialFunction(x: Double) = {
    if (squareRoot.isDefinedAt(x)) {
      s"Square root of $x is ${squareRoot(x)}"
    } else {
      s"Cannot calculate square root for $x"
    }
  }

  def getSqrtRootMessageTotalFunction(x: Double) = {
   squareRoot.lift(x).map(result => s"Square root of ${x} is ${result}")
     .getOrElse(s"Cannot calculate square root for $x")
  }

  def add5(x: Int) = x + 5
  def isEven(x: Int) = x % 2 == 0

  val funcAdd5 = add5 _
  val funcIsEven = isEven _

  val sayHello: Future[Option[String]] = Future.successful(Some("Say hello to"))
  val firstname: Future[String] = Future.successful("Fabio")

  def getGreetingsBasic() = {
    val maybeHello: Future[String] = for {
      hello <- sayHello
      name  <- firstname
    } yield s"${hello.get} $name"

    Await.result(maybeHello, 1.second)
  }

  def getGreetingsMonadTranformer() = {
    val maybeHello: OptionT[Future, String] = for {
      hello <- OptionT(sayHello)
      name  <- OptionT.liftF(firstname)
    } yield s"$hello $name"

    val result: Future[Option[String]] = maybeHello.value

    Await.result(result, 1.second)
  }

  val optionLength: Option[String] => Option[Int] = Functor[Option].lift(_.length)

}
