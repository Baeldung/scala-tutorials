package com.baeldung.scala.catseffects

import cats.effect.kernel.CancelScope.Cancelable
import cats.effect.{IO, Resource}
import cats.effect.unsafe.implicits.global
import cats.implicits.catsSyntaxTuple2Parallel
import org.scalatest.Ignore
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

import java.util.concurrent.atomic.AtomicBoolean
import scala.concurrent.duration.DurationInt

// todo: Flakiness due to https://jira.baeldung.com/browse/SCALA-635
// temporarily ignoring the test until the issue is fixed.
@Ignore
class CancellationSpec extends AnyWordSpec with Matchers {

  "Cancellation" should {

    "cancel the fiber directly and execute the action on cancellation" in {
      val flag = new AtomicBoolean(false)
      val io = IO.println("ready to cancel the fiber") >> IO.sleep(
        5.seconds
      ) >> IO.println("Hello, World!")
      val onCancel =
        IO.println("cancellation signal received") >> IO.delay(flag.set(true))
      val cancelFiber = Cancellation.cancelFiberDirectly(io, onCancel)
      cancelFiber.unsafeRunSync()
      flag.get() shouldBe true
    }

    "naive parMap works" in {
      def tickingClock: IO[Unit] =
        for {
          _ <- IO.println(s"current time = ${System.currentTimeMillis()}")
          _ <- IO.sleep(250.millisecond)
          _ <- tickingClock
        } yield ()
      val error: IO[Unit] =
        IO.sleep(1.second) *> IO.raiseError(new RuntimeException("boom!"))

      val parMapNaive_2 = Cancellation.naiveParMap_2(
        tickingClock,
        error,
        IO.println("[2] tickingClock was cancelled"),
        IO.println("[2] error was cancelled"),
        (_: Unit, _: Unit) => println("[2] Exit")
      )

      val res = parMapNaive_2.handleError(e => e.getMessage).unsafeRunSync()
      res shouldBe "boom!"
    }

    "send emails if the cancellation signal came during the sending process" in {
      val sendEmails: IO[Unit] =
        Cancellation
          .sendEmailsUncancelable("Exam on FP is shifted to Jan 11, 2024")
          .onCancel(IO.println("cancellation signal received"))
      IO.race(IO.sleep(1.seconds), sendEmails).unsafeRunSync()
    }

    "if one of the effects running in parallel throws an error, the second one in cancelled, but it won't affect uncancelalble regions" in {
      val flag = new AtomicBoolean(false)
      val res = (
        IO.sleep(500.millisecond) >> IO.raiseError(
          new RuntimeException("Boom!")
        ),
        IO.uncancelable(_ =>
          IO.sleep(1.second) >> IO.println("Hey there") >> IO.delay(
            flag.set(true)
          )
        )
      ).parTupled
        .handleError(_ => flag.get)
        .unsafeRunSync()
      res shouldBe true
    }

    "partially cancel the unancelable IO" in {
      var counter = 0
      val example: IO[Unit] = {
        val flag = new AtomicBoolean(false)
        val ioa = IO.blocking {
          while (!flag.get()) {
            Thread.sleep(100)
            println(s"counter = $counter")
            counter += 1
          }
        }

        ioa.cancelable(
          IO.println("executing the finalizer...") >> IO.delay(flag.set(true))
        )
      }

      (for {
        fiber <- example.start
        _ <- IO.sleep(500.millisecond)
        _ <- IO.println("cancelling the fiber")
        _ <- fiber.cancel
        _ <- fiber.join
      } yield ()).unsafeRunSync()

      counter <= 6 shouldBe true
    }

  }

}
