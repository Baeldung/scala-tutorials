package com.baeldung.scala.zio.testingapplications

import zio.ZIO

object TestingApplicationsExamples {
  def returnString(str: String): ZIO[Any, Nothing, String] =
    ZIO.succeed(str)
}
