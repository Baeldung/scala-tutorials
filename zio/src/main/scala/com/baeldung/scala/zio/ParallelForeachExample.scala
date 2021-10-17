package com.baeldung.scala.zio

import zio._

import scala.io.{Codec, Source}

object ParallelForeachExample extends ZIOAppDefault {
  def fetchUrl(url: String) = ZIO.attemptBlocking(
    Source.fromURL(url)(Codec.ISO8859).mkString
  )

  val myApp =
    ZIO.foreachPar(List("https://google.com", "https://zio.dev/")) { page =>
      fetchUrl(page).debug
    }

  def run = myApp
}
