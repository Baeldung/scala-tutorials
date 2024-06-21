package com.baeldung.scala.freemonad

import scala.concurrent.{Future, ExecutionContext}
import scala.util.{Try, Success, Failure}
import scala.io.StdIn.readChar
import scala.reflect.*

given ExecutionContext = ExecutionContext.Implicits.global

trait Monad[F[_]]:
  def flatMap[A, B](fa: F[A])(f: (A) => F[B]): F[B]

  def pure[A](a: A): F[A]

  def map[A, B](fa: F[A])(f: A => B): F[B] =
    flatMap(fa)(a => pure(f(a)))

// List composition example:

lazy val listComposition =
  for
    number <- 0 to 9
    letter <- 'A' to 'Z'
  yield s"$number$letter"

// Which the compiler transforms to:

lazy val desugaredListComposition =
  (0 to 9).flatMap: number =>
    ('A' to 'Z').map: letter =>
      s"$number$letter"

// A functor is simpler and less powerful than a monad:

trait Functor[F[_]]:
  def map[A, B](fa: F[A])(f: A => B): F[B]

// A transformation between two higher-kinded types with the same type parameter:

trait ~>[F[_], G[_]]:
  def apply[A: Typeable](f: F[A]): G[A]

// Free allows us to lift a functor with monadic composition as a data structure:

sealed trait Free[F[_], A: Typeable]:
  def map[B: Typeable](f: A => B): Free[F, B] =
    FlatMap(this, (a: A) => Pure(f(a)))
  def flatMap[B: Typeable](f: A => Free[F, B]): Free[F, B] = FlatMap(this, f)

  def foldMapAs[G[_]: Monad](using F ~> G): G[A] = this match
    case Pure(value) => summon[Monad[G]].pure(value)
    case FlatMap(sub, f) =>
      summon[Monad[G]]
        .flatMap(sub.foldMapAs[G]): in =>
          f(in).foldMapAs[G]
    case Suspend(s) => summon[F ~> G](s)

final case class Pure[F[_], A: Typeable](value: A) extends Free[F, A]
final case class FlatMap[F[_], A: Typeable, B: Typeable](
  sub: Free[F, A],
  f: A => Free[F, B]
) extends Free[F, B]
final case class Suspend[F[_], A: Typeable](s: F[A]) extends Free[F, A]

// We define a non-monadic type:

trait LazyCatchable[+A]:
  def run(): Either[Catch, A]

final class Lazy[A](value: => A) extends LazyCatchable[A]:
  def run(): Either[Catch, A] = Try(value) match
    case Success(value) => Right(value)
    case Failure(e)     => Left(Catch(e))

final case class Catch(e: Throwable) extends LazyCatchable[Nothing]:
  def run(): Either[Catch, Nothing] = Left(this)

// We can write monadic programs with it:

lazy val sumProgram: Free[LazyCatchable, Int] =
  for
    a <- Suspend(Lazy(1))
    b <- Suspend(Lazy(2))
    result <- Pure(a + b)
  yield result

// Which is translated by the compiler to this:

lazy val desugaredSumProgram =
  FlatMap(
    Suspend(Lazy(1)),
    (num1: Int) =>
      FlatMap(
        Suspend(Lazy(2)),
        (num2: Int) => Pure(num1 + num2)
      )
  )

// We provide a ~> to a Future:

given LazyCatchable2Future: (LazyCatchable ~> Future) with
  def apply[A: Typeable](f: LazyCatchable[A]): Future[A] = f match
    case Catch(e) => Future.failed(e)
    case lazyValue: Lazy[_] =>
      Future:
        lazyValue.run() match
          case Left(Catch(e))             => throw e
          case Right(value: A @unchecked) => value

// We define a Monad instance for Future:

given FutureMonad: Monad[Future] with
  def flatMap[A, B](fa: Future[A])(f: (A) => Future[B]): Future[B] =
    fa.flatMap(f)

  def pure[A](a: A): Future[A] = Future(a)

  override def map[A, B](fa: Future[A])(f: A => B): Future[B] = fa.map(f)

// We can then convert our sumProgram to a Future:

lazy val sumProgramFuture: Future[Int] = sumProgram.foldMapAs[Future](using
  FutureMonad,
  LazyCatchable2Future
) // Future computes to 3

// Let's consider a more advanced workflow DSL:

enum WorkflowCommand:
  case FeelInspiredToLearn
  case LikeFriendlyEnvironments
  case WantToHelpPeopleBuildConfidenceCoding
  case JoinBaeldungAsAWriter

// We can then define our logic:

def command[C <: WorkflowCommand](c: => C): Free[LazyCatchable, C] = Suspend(
  Lazy(c)
)

lazy val joinBaeldungWorkflow: Free[LazyCatchable, WorkflowCommand] =
  for
    _ <- command(WorkflowCommand.FeelInspiredToLearn)
    _ <- command(WorkflowCommand.LikeFriendlyEnvironments)
    _ <- command(WorkflowCommand.WantToHelpPeopleBuildConfidenceCoding)
    `reachOutToday!` <- Pure(WorkflowCommand.JoinBaeldungAsAWriter)
  yield `reachOutToday!`

// Then we define a translation to Future:

given BaeldungWorkflowInterpreter: (LazyCatchable ~> Future) with
  private def askQuestion(question: String, repeat: Boolean = false): Boolean =
    if repeat then print(s"\nInvalid response: try again (y or n) ")
    else print(s"\n$question (y or n) ")

    readChar() match
      case 'y' | 'Y' => true
      case 'n' | 'N' => false
      case _         => askQuestion(question, true)

  private def step[C <: WorkflowCommand](
    question: String,
    command: C,
    error: String
  ): Future[C] = Future:
    if askQuestion(question) then command
    else throw new Exception(error)

  def apply[A: Typeable](f: LazyCatchable[A]): Future[A] = f match
    case Catch(e) => Future.failed(e)
    case lazyCmd: Lazy[_] =>
      lazyCmd.run() match
        case Left(Catch(e)) => Future.failed(e)
        case Right(command: WorkflowCommand) =>
          command match
            case WorkflowCommand.FeelInspiredToLearn =>
              step(
                question = "Do you feel inspired to learn Scala?",
                command = command,
                error =
                  "Baeldung has tutorials for other technologies too, like Java."
              )
            case WorkflowCommand.LikeFriendlyEnvironments =>
              step(
                question = "Do you like friendly environments?",
                command = command,
                error = "Bye."
              )
            case WorkflowCommand.WantToHelpPeopleBuildConfidenceCoding =>
              step(
                question =
                  "Do you want to help people build confidence coding?",
                command = command,
                error = "Baeldung tutorials are reliable and informative."
              )
            case WorkflowCommand.JoinBaeldungAsAWriter =>
              Future.successful(command)
        case Right(misc) => Future.successful(misc)

// The translation is then very simple and intuitive:

lazy val joinBaeldung: Future[WorkflowCommand] = joinBaeldungWorkflow
  .foldMapAs[Future](using FutureMonad, BaeldungWorkflowInterpreter)
