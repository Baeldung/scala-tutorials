package com.baeldung.scala.zio

import zio._
import zio.stm._

object ClassicSTMTransferExample extends ZIOAppDefault {
  def withdraw(from: stm.TRef[Int], amount: Int): USTM[Unit] = {
    for {
      sender <- from.get
      _ <- if (amount <= sender) from.update(_ - amount) else STM.unit
    } yield ()
  }

  def deposit(to: stm.TRef[Int], amount: Int): USTM[Unit] = {
    to.update(_ + amount)
  }

  def transfer(
    from: TRef[Int],
    to: TRef[Int],
    amount: Int
  ): ZIO[Any, String, Unit] =
    STM.atomically {
      for {
        _ <- withdraw(from, amount)
        _ <- deposit(to, amount)
      } yield ()
    }

  val myApp = for {
    from <- TRef.make(10).commit
    to <- TRef.make(0).commit
    _ <- transfer(from, to, amount = 5)
  } yield ()

  def run = myApp
}
