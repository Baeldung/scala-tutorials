package com.baeldung.csv.reader

import org.scalatest.matchers.should
import org.scalatest.wordspec.AnyWordSpec

import java.io.{File, PrintWriter}
import java.nio.file.{Files, Path}
import java.util.UUID

class ScalaCSVReadersTest extends AnyWordSpec with should.Matchers {

  "SimpleCSVReader" should {
    "read a csv file" in {
      commonTestBody(new SimpleCSVReader, randomTmpFile().toFile)
    }
  }

  "ScalaCSVReader" should {
    "read a csv file" in {
      commonTestBody(new ScalaCSVReader, randomTmpFile().toFile)
    }
  }

  "OpenCSVReader" should {
    "read a csv file" in {
      commonTestBody(new OpenCSVReader, randomTmpFile().toFile)
    }
  }

  "ApacheCommonsCSVReader" should {
    "read a csv file" in {
      commonTestBody(new ApacheCommonsCSVReader, randomTmpFile().toFile)
    }
  }

  private def commonTestBody(
    testee: CommaSeparatedValuesReader,
    file: File
  ): Unit = {
    val result = testee.read(file)
    assert(result.headers === List("column1", "column2"))
    assert(
      result.rows === List(
        List("1.1", "1.2"),
        List("2.1", "2.2")
      )
    )
  }

  private def randomTmpFile(): Path = {
    val path = Files.createTempFile(
      s"persons-${UUID.randomUUID().toString.take(8)}",
      "csv"
    )
    val writer = new PrintWriter(path.toFile)
    writer.println(List("column1", "column2").mkString(","))
    writer.println(List("1.1", "1.2").mkString(","))
    writer.println(List("2.1", "2.2").mkString(","))
    writer.close()
    path
  }

}
