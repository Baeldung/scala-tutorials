package com.baeldung.scala.catseffects

import cats.syntax.applicative._
import cats.{Applicative, ApplicativeError}

import scala.util.{Failure, Success, Try}

object ErrorHandling {

  trait Calculator[F[_]] {
    def calculate(f: => Int): F[Int]
  }

  class CalculatorImpl[F[_]: Applicative]()(implicit
    m: ApplicativeError[F, Throwable]
  ) extends Calculator[F] {
    override def calculate(f: => Int): F[Int] = Try(f) match {
      case Success(res) => res.pure[F]
      case Failure(_) =>
        m.raiseError[Int](new RuntimeException("Calculation failed"))
    }
  }

}
