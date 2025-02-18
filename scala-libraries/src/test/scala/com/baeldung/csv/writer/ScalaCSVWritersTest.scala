package com.baeldung.csv.writer

import org.scalatest.matchers.should
import org.scalatest.wordspec.AnyWordSpec

import java.io.{BufferedReader, File, FileInputStream, InputStreamReader}
import java.nio.file.{Files, Path}
import java.util.UUID
import scala.annotation.tailrec;

class ScalaCSVWritersTest extends AnyWordSpec with should.Matchers {

  "SimpleCSVWriter" should {
    "write a csv file" in {
      commonTestBody(new SimpleCSVWriter, randomTmpFile().toFile)
    }
  }

  "ScalaCSVWriter" should {
    "write a csv file" in {
      commonTestBody(new ScalaCSVWriter, randomTmpFile().toFile)
    }
  }

  "OpenCSVWriter" should {
    "write a csv file" in {
      commonTestBody(new OpenCSVWriter, randomTmpFile().toFile)
    }
  }

  "ApacheCommonsCSVWriter" should {
    "write a csv file" in {
      commonTestBody(new ApacheCommonsCSVWriter, randomTmpFile().toFile)
    }
  }

  private def commonTestBody(
    testee: CommaSeparatedValuesWriter,
    tmpFile: File
  ): Unit = {
    testee.write(
      tmpFile,
      List("column1", "column2"),
      List(
        List("c1.1", "c2.1"),
        List("c1.2", "c2.2")
      )
    )
    val result = read(tmpFile)
    assert(result.headOption.contains("column1,column2"))
    assert(result.tail.headOption.contains("c1.1,c2.1"))
    assert(result.tail.tail.headOption.contains("c1.2,c2.2"))
  }

  private def read(file: File): Seq[String] = {
    val in = new InputStreamReader(new FileInputStream(file))
    val bufferedReader = new BufferedReader(in)

    @tailrec
    def readLinesRecursively(
      currentBufferedReader: BufferedReader,
      result: Seq[String]
    ): Seq[String] = {
      currentBufferedReader.readLine() match {
        case null => result
        case line =>
          readLinesRecursively(
            currentBufferedReader,
            result :+ line
          )
      }
    }

    val csvLines = readLinesRecursively(bufferedReader, List())

    bufferedReader.close()

    csvLines
  }

  private def randomTmpFile(): Path = {
    Files.createTempFile(
      s"persons-${UUID.randomUUID().toString.take(8)}",
      "csv"
    )
  }

}
