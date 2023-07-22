package com.baeldung.scala.zio.fibers

import zio._

object PastaCookingFiberAppExample extends ZIOAppDefault {

  private def boilWater(): ZIO[Any, Nothing, Unit] =
    for {
      _ <- ZIO.succeed(println("Water put on stove..."))
      _ <- delay(100.milli)
      _ <- ZIO.succeed(println("Water boiled!"))
    } yield ()

  private def boilPasta(): ZIO[Any, Nothing, Unit] =
    for {
      _ <- ZIO.succeed(println("Put pasta in boiling water..."))
      _ <- delay(1.second)
      _ <- ZIO.succeed(println("Pasta ready!"))
    } yield ()

  private def prepareIngredient(ingredient: String): ZIO[Any, Nothing, Unit] =
    for {
      _ <- ZIO.succeed(println(s"Preparing $ingredient..."))
      _ <- delay(300.millis)
      _ <- ZIO.succeed(println(s"$ingredient ready"))
    } yield ()

  private def makeSauce(): ZIO[Any, Nothing, Unit] =
    for {
      _ <- ZIO.succeed(println(s"Preparing sauce..."))
      _ <- delay(1.second)
      _ <- ZIO.succeed(println(s"Sauce is ready"))
    } yield ()

  private def orderFood(): ZIO[Any, Nothing, Unit] =
    for {
      _ <- ZIO.succeed(println("Ordering some food..."))
      _ <- delay(1.second)
      _ <- ZIO.succeed(println("Food arrived!"))
    } yield ()

  private def delay(duration: Duration): ZIO[Any, Nothing, Unit] =
    ZIO.sleep(duration)

  private val cleanup: ZIO[Any, Nothing, Unit] =
    for {
      _ <- ZIO.succeed(println(s"Cleaning up kitchen and delivery packages..."))
      _ <- delay(10.millis)
      _ <- ZIO.succeed(println("All clean"))
    } yield ()

  def pastaApp: ZIO[Any, Nothing, Unit] =
    for {
      waterFiber <- boilWater().fork
      pastaFiber <- waterFiber.await.zip(boilPasta()).fork
      tomatoFiber <- prepareIngredient("tomato").fork
      onionFiber <- tomatoFiber.await.zip(prepareIngredient("onion")).fork
      sauceFiber <- onionFiber.await.zip(makeSauce()).fork
      _ <- pastaFiber.zip(sauceFiber).interrupt
      orderFiber <- orderFood().fork
      _ <- orderFiber.join.ensuring(cleanup)
    } yield ()

  override def run: ZIO[Any with ZIOAppArgs with Scope, Any, Any] = pastaApp
}
