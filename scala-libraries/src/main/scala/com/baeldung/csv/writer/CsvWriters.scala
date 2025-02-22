package com.baeldung.csv.writer

import scala.util.{Failure, Success, Try}

object CsvWriters {

  def main(args: Array[String]): Unit = {

    val fileName = "foo.csv"
    val headers = List("id", "name")
    val rows = List(
      List("1", "Manolis"),
      List("2", "Thanasis"),
      List("3", "Stefanos")
    )

    val simpleCSVWriter = new SimpleCSVWriter
    val openCSVWriter = new OpenCSVWriter
    val scalaCSVWriter = new ScalaCSVWriter
    val apacheCommonsCSVWriter = new ApacheCommonsCSVWriter

    handleFailure(simpleCSVWriter.write(fileName, headers, rows))
    handleFailure(openCSVWriter.write(fileName, headers, rows))
    handleFailure(scalaCSVWriter.write(fileName, headers, rows))
    handleFailure(apacheCommonsCSVWriter.write(fileName, headers, rows))
  }

  private def handleFailure(tryWrite: Try[Unit]): Unit = {
    tryWrite match {
      case Success(_) =>
      case Failure(exception) =>
        println(
          s"Something went wrong during CSV writing: ${exception.getMessage}"
        )
    }
  }

}
