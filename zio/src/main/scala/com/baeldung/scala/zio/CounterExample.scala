package com.baeldung.scala.zio

import zio._

object CounterExample extends ZIOAppDefault {
  def run =
    for {
      counter <- Ref.make(0)
      _ <- ZIO.foreachPar((1 to 100).toList) { _ =>
        counter.updateAndGet(_ + 1)
          .flatMap(reqNumber => Console.printLine(s"request number: $reqNumber"))
      }
      reqCounts <- counter.get
      _ <- Console.printLine(s"total requests performed: $reqCounts")
    } yield ()
}
