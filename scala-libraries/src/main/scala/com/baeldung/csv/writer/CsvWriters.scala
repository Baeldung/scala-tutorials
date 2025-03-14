package com.baeldung.csv.writer

import java.io.File
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

    handleFailure(
      simpleCSVWriter.write(new File(s"simple-$fileName"), headers, rows)
    )
    handleFailure(
      openCSVWriter.write(new File(s"openCSV-$fileName"), headers, rows)
    )
    handleFailure(
      scalaCSVWriter.write(new File(s"scalaCSV-$fileName"), headers, rows)
    )
    handleFailure(
      apacheCommonsCSVWriter.write(
        new File(s"apacheCommons-$fileName"),
        headers,
        rows
      )
    )
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
