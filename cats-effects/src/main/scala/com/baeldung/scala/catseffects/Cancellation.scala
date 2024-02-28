package com.baeldung.scala.catseffects

import cats.effect.{IO, Resource}
import cats.implicits.{catsSyntaxParallelTraverse1, toTraverseOps}

import java.io.{InputStream, OutputStream}
import java.util.concurrent.atomic.AtomicBoolean
import scala.concurrent.duration.DurationInt

object Cancellation {

  def cancelFiberDirectly[A](io: IO[A], onCancellation: => IO[Unit]): IO[Unit] =
    for {
      fiber <- io.onCancel(onCancellation).start
      _ <- fiber.cancel
      _ <- fiber.join
    } yield ()

  def naiveParMap_1[A, B, C](
    ioa: IO[A],
    iob: IO[B],
    onCancelA: IO[A],
    onCancelB: IO[B],
    f: (A, B) => C
  ): IO[C] =
    for {
      fiberA <- ioa.start
      fiberB <- iob.start
      a <- fiberA.joinWith(onCancelA).onError(_ => fiberB.cancel)
      b <- fiberB.joinWith(onCancelB).onError(_ => fiberA.cancel)
    } yield f(a, b)

  def naiveParMap_2[A, B, C](
    ioa: IO[A],
    iob: IO[B],
    onCancelA: IO[A],
    onCancelB: IO[B],
    f: (A, B) => C
  ): IO[C] =
    for {
      fiberA <- ioa.start
      fiberB <- iob.start
      fiberAj = fiberA.joinWith(onCancelA).onError(_ => fiberB.cancel)
      fiberBj = fiberB.joinWith(onCancelB).onError(_ => fiberA.cancel)
      regA <- fiberAj.start
      regB <- fiberBj.start
      a <- regA.joinWith(onCancelA)
      b <- regB.joinWith(onCancelB)
    } yield f(a, b)

  def getEmails(): IO[List[String]] = IO(
    List("gmail@bob.com", "alice@tech.org", "john@doe.org")
  )

  def send(message: String)(email: String): IO[Unit] = IO.sleep(
    1.seconds
  ) >> IO.println(s"sending email to $email with $message")

  def sendEmailsUncancelable(message: String): IO[Unit] =
    for {
      emails <- getEmails()
      _ <- IO.println("ready to send emails")
      _ <- IO.uncancelable(_ => emails.traverse(send(message)))
      _ <- IO.println("emails are sent")
    } yield ()

  def sendEmailsUncancelableResource(message: String) =
    Resource
      .make(getEmails())(emails => IO.println(s"emails $emails are released"))
      .use(emails =>
        IO.println("ready to send emails") >>
          IO.uncancelable(_ => emails.traverse(send(message))) >>
          IO.println("emails are sent")
      )

  def sendEmailsUncancelableBracket(message: String): IO[Unit] =
    (getEmails()).bracket(emails =>
      IO.println("ready to send emails") >>
        IO.uncancelable(_ => emails.traverse(send(message))) >>
        IO.println("emails are sent")
    )(emails => IO.println(s"[bracket] emails $emails are released"))

}
