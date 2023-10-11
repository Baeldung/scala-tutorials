package com.baeldung.scala.zio.errorhandling

import zio.{Schedule, ZIO}

import java.io.IOException

object ErrorHandling {

  private def stubbedResource(
    isFail: Boolean = false
  ): ZIO[Any, IOException, String] = {
    if (isFail) ZIO.fail(new IOException()) else ZIO.succeed("success")
  }

  def usingEither: ZIO[Any, Nothing, Either[IOException, String]] = {
    stubbedResource().either
  }

  def usingOrElse: ZIO[Any, IOException, String] = {
    stubbedResource(true).orElse(stubbedResource(false))
  }

  def usingCatchAll: ZIO[Any, IOException, String] = {
    stubbedResource(true).catchAll(_ =>
      for {
        _ <- ZIO.logError("Some error occurred")
        resource <- stubbedResource(false)
      } yield resource
    )
  }

  def usingCatchSome: ZIO[Any, IOException, String] = {
    stubbedResource(true).catchSome { case _: IOException =>
      ZIO.logError("Some error occurred")
      stubbedResource(false)
    }
  }

  def usingFold: ZIO[Any, Nothing, String] = {
    stubbedResource(true).fold(
      fail => "some default",
      success => success
    )
  }

  def usingFoldZIO: ZIO[Any, IOException, String] = {
    stubbedResource(true).foldZIO(
      fail => stubbedResource(false),
      success => ZIO.succeed(success)
    )
  }

  def usingRetry: ZIO[Any, IOException, String] = {
    stubbedResource(true).retry(Schedule.recurs(3))
  }

  def usingRetryOrElse: ZIO[Any, IOException, String] = {
    stubbedResource(true).retryOrElse(
      Schedule.recurs(3),
      (_, _: Long) => stubbedResource(false)
    )
  }
}
