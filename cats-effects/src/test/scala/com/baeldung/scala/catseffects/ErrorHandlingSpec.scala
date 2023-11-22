package com.baeldung.scala.catseffects

import cats.effect.IO
import cats.effect.unsafe.implicits.global
import cats.{Applicative, ApplicativeError, MonadError, MonadThrow}
import com.baeldung.scala.catseffects.ErrorHandling.{Calculator, CalculatorImpl}
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

import scala.util.Try
import scala.util.control.NoStackTrace;

class ErrorHandlingSpec extends AnyWordSpec with Matchers {

  "Calculator" should {
    "return left value using attempt in case of division by zero" in {
      val calculator = new CalculatorImpl[IO]()
      val res = calculator.calculate(5 / 0).attempt.unsafeRunSync()
      res.isLeft shouldBe true
    }
  }

  import ErrorHandlingSpec._

  "IO error handling built in methods" should {
    import cats.syntax.monadError._
    "adaptError" in {
      def calculate(f: => Int): IO[Int] =
        IO(f).adaptError { case _: ArithmeticException =>
          new RuntimeException("Calculation failed")
        }

      val res = calculate(5 / 0)
      res
        .handleError(e => e.getMessage)
        .unsafeRunSync() shouldBe "Calculation failed"
    }

    "recover from the errors" in {
      def calculate(f: => Int): IO[Int] = {
        IO(f).recover {
          case _: ArithmeticException   => -1
          case _: NumberFormatException => -2
        }
      }

      val res_0 = calculate(5 / 0)
      res_0.unsafeRunSync() shouldBe -1

      val res_1 = calculate("5 * 3".toInt / 1)
      res_1.unsafeRunSync() shouldBe -2

      val res_2 = calculate(15 / 1)
      res_2.unsafeRunSync() shouldBe 15
    }

    "execute some actions onError" in {
      var status = 0
      def calculate(f: => Int): IO[Int] =
        IO(f)
          .onError {
            case _: ArithmeticException   => IO { status = -1 }
            case _: NumberFormatException => IO { status = -2 }
          }
          .handleError {
            case _: ArithmeticException   => -1
            case _: NumberFormatException => -2
          }
      calculate(5 / 0).unsafeRunSync() shouldBe -1
      status shouldBe -1

      calculate("5 * 3".toInt).unsafeRunSync() shouldBe -2
      status shouldBe -2
    }
    "execute action in the middle of error handling" in {
      var errorCounter = 0
      def calculateAndExecuteAction(f: => Int, action: => IO[Unit]): IO[Int] =
        IO(f).handleErrorWith {
          case _: ArithmeticException   => action *> IO.pure(-1)
          case _: NumberFormatException => action *> IO.pure(-2)
        }

      val res_0 = calculateAndExecuteAction(5 / 0, IO { errorCounter += 1 })
      res_0.unsafeRunSync() shouldBe -1
      errorCounter shouldBe 1

      val res_1 =
        calculateAndExecuteAction("n".toInt / 1, IO { errorCounter += 1 })
      res_1.unsafeRunSync() shouldBe -2
      errorCounter shouldBe 2
    }
  }

  "Error handling for Either with ApplicativeError" should {
    import cats.syntax.applicative._
    import cats.syntax.applicativeError._

    "handle error if it occurred right away" in {
      type ErrorOr[A] = Either[Throwable, A]
      val calculator = new CalculatorImplAE[ErrorOr]
      val res = calculator.calculate(5 / 0).handleErrorWith {
        case _: ArithmeticException   => (-1).pure[ErrorOr]
        case _: NumberFormatException => (-2).pure[ErrorOr]
      }
      res shouldBe Right(-1)
    }

    "if the error handling is suspended, then the error handling fails" in {
      type ErrorOr[A] = Either[Throwable, A]
      val calculator = new CalculatorImplAE[ErrorOr]
      val failed = calculator.calculate(5 / 0)
      failed.handleErrorWith {
        case _: ArithmeticException   => (-1).pure[ErrorOr]
        case _: NumberFormatException => (-2).pure[ErrorOr]
      }

      val recovered = calculator.calculate(5 / 0).handleErrorWith {
        case _: ArithmeticException   => (-1).pure[ErrorOr]
        case _: NumberFormatException => (-2).pure[ErrorOr]
      }

      failed.isLeft shouldBe true
      recovered shouldBe Right(-1)
    }
  }

  "Error handling for Either with MonadError" should {
    import cats.syntax.applicative._
    import cats.syntax.applicativeError._
    import cats.syntax.monadError._

    "raise error if the predicate fails via ensure" in {
      type ErrorOr[A] = Either[Throwable, A]
      val calculator = new CalculatorImplME[ErrorOr]

      val resValid = calculator
        .calculate(5 / 1)
        .ensure(new RuntimeException("Bad result"))(_ > 0)
      val resInvalid = calculator
        .calculate(-1)
        .ensure(new RuntimeException("Bad result"))(_ > 0)

      resValid shouldBe Right(5)
      resInvalid.isLeft shouldBe true
    }

    "raise error if the predicate fails via flatMap" in {
      type ErrorOr[A] = Either[Throwable, A]
      val calculator = new CalculatorImplME[ErrorOr]

      val resValid = calculator.calculate(5 / 1).flatMap {
        case x if x > 0 => x.pure[ErrorOr]
        case _ => new RuntimeException("Bad result").raiseError[ErrorOr, Int]
      }
      val resInvalid = calculator
        .calculate(-1)
        .ensure(new RuntimeException("Bad result"))(_ > 0)

      resValid shouldBe Right(5)
      resInvalid.isLeft shouldBe true
    }

    "rethrow the error if it is inside the Either" in {
      val calculator = new CalculatorImplME[IO]
      def calculateSafely(f: => Int): IO[Int] =
        calculator.calculate(f).attempt.rethrow.handleErrorWith {
          case _: ArithmeticException   => IO.pure(-1)
          case _: NumberFormatException => IO.pure(-2)
        }

      val resInvalid = calculateSafely(5 / 0)
      val resValid = calculateSafely(5 / 1)

      resInvalid.unsafeRunSync() shouldBe -1
      resValid.unsafeRunSync() shouldBe 5
    }

    "rethrow the error" in {
      import cats.syntax.applicative._
      import cats.syntax.applicativeError._
      def calculate[F[_]: Applicative](f: => Int)(implicit
        ae: ApplicativeError[F, Throwable]
      ): F[Either[Throwable, Int]] =
        ae.fromTry(Try(f)).attempt

      def calculateSafely[F[_]](f: => Int)(implicit me: MonadThrow[F]): F[Int] =
        calculate(f).rethrow.handleErrorWith {
          case _: ArithmeticException   => (-1).pure[F]
          case _: NumberFormatException => (-2).pure[F]
        }

      val resInvalid = calculateSafely[IO](5 / 0)
      val resValid = calculateSafely[IO](5 / 1)

      resInvalid.unsafeRunSync() shouldBe -1
      resValid.unsafeRunSync() shouldBe 5
    }

    "attemptTap returns the error if it occurs and returns the value otherwise" in {
      import cats.syntax.applicative._
      def calculate[F[_]: Applicative](f: => Int)(implicit
        ae: ApplicativeError[F, Throwable]
      ): F[Int] =
        ae.fromTry(Try(f))

      def calculateOrRaise[F[_]](f: => Int)(implicit
        me: MonadThrow[F]
      ): F[Int] =
        calculate(f).attemptTap {
          case Left(_) =>
            new RuntimeException("Calculation failed").raiseError[F, Unit]
          case Right(_) => ().pure[F]
        }

        val resInvalid = calculateOrRaise[IO](5 / 0).attempt
        val resValid = calculateOrRaise[IO](5 / 1)

        resInvalid.unsafeRunSync().isLeft shouldBe true
        resValid.unsafeRunSync() shouldBe 5
    }

    "if recover throws the error, then this error will be returned" in {
      def calculate[F[_]: Applicative](f: => Int)(implicit
        ae: ApplicativeError[F, Throwable]
      ): F[Int] =
        ae.fromTry(Try(f))

      def recover[F[_]]()(implicit ae: ApplicativeError[F, Throwable]): F[Int] =
        new RuntimeException("Calculation failed").raiseError[F, Int]

      def calculateOrRaise[F[_]](f: => Int)(implicit
        me: MonadThrow[F]
      ): F[Int] =
        calculate(f).recoverWith {
          case _: ArithmeticException   => recover()
          case _: NumberFormatException => recover()
        }

      val resInvalid = calculateOrRaise[IO](5 / 0).handleErrorWith {
        case _: RuntimeException => IO.pure(-1)
        case _                   => IO.pure(-2)
      }
      val resValid = calculateOrRaise[IO](5 / 1)

      resInvalid.unsafeRunSync() shouldBe -1
      resValid.unsafeRunSync() shouldBe 5
    }

  }

  "Domain errors " should {
    "be thrown if there's an implicit instance of MonadError[F, DomainError]" in {
      object ErrorHandlingSpec {
        import cats.syntax.applicative._

        sealed trait DomainError extends NoStackTrace
        case object NotFound extends DomainError
        case object InvalidInput extends DomainError

        trait RaiseCustomError[F[_]] {
          def raiseCustomError[A](e: DomainError): F[A]
        }

        object RaiseCustomError {
          implicit def instance[F[_]](implicit
            M: MonadError[F, Throwable]
          ): RaiseCustomError[F] =
            new RaiseCustomError[F] {
              def raiseCustomError[A](e: DomainError): F[A] = M.raiseError(e)
            }
        }

        def serve[F[_]: Applicative](inOpt: Option[String])(implicit
          R: RaiseCustomError[F]
        ): F[String] =
          inOpt match {
            case None                   => R.raiseCustomError(NotFound)
            case Some(in) if in.isEmpty => R.raiseCustomError(InvalidInput)
            case Some(in)               => in.pure[F]
          }
      }
      import ErrorHandlingSpec._
      val resNone = serve[IO](None).handleErrorWith {
        case NotFound     => IO.pure("Not found")
        case InvalidInput => IO.pure("Invalid input")
      }
      val resEmpty = serve[IO](Some("")).handleErrorWith {
        case NotFound     => IO.pure("Not found")
        case InvalidInput => IO.pure("Invalid input")
      }

      implicit val i = implicitly[MonadError[IO, Throwable]]

      resNone.unsafeRunSync() shouldBe "Not found"
      resEmpty.unsafeRunSync() shouldBe "Invalid input"
    }
  }

}

object ErrorHandlingSpec {

  class CalculatorImplAE[F[_]](implicit ae: ApplicativeError[F, Throwable])
    extends Calculator[F] {
    override def calculate(f: => Int): F[Int] = ae.fromTry(Try(f))
  }

  class CalculatorImplME[F[_]](implicit me: MonadError[F, Throwable])
    extends Calculator[F] {
    import cats.syntax.applicative._
    import cats.syntax.applicativeError._
    import cats.syntax.functor._

    override def calculate(f: => Int): F[Int] = {
      me.fromTry(Try(f)).handleErrorWith {
        case e: ArithmeticException =>
          println(e.getMessage).pure[F].map(_ => -1)
        case e: NumberFormatException =>
          println(e.getMessage).pure[F].map(_ => -2)
      }
    }
  }

}
