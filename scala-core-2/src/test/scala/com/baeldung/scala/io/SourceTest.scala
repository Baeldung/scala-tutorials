package com.baeldung.scala.io

import org.scalatest.{BeforeAndAfterAll, Matchers, WordSpec}

import scala.io.Source

class SourceTest extends WordSpec with Matchers with BeforeAndAfterAll {
  lazy val sourceFromUrl: Source = Source.fromURL("https://google.com")
  lazy val sourceFromClassPath: Source = Source.fromResource("com.baeldung.scala.io/file_in_classpath.txt")
  lazy val sourceFromFile: Source = Source.fromFile("./some_text_file")
  lazy val sourceFromFileWithCustomEncoder: Source = Source.fromFile("./some_text_file", enc = "Cp1252")

  "Source" should {
    "eagerly convert into a string" in {
      val oneLineSource = Source.fromResource("com.baeldung.scala.io/one_line_string.txt")
      try {
        oneLineSource.mkString shouldEqual "One line string"
      } finally {
        oneLineSource.close()
      }
    }

    "process data line by line" in {
      val fourLinesSource = Source.fromResource("com.baeldung.scala.io/four_lines_string.txt")
      try {
        fourLinesSource.getLines().zipWithIndex.foreach {
          case (line, num) => line shouldEqual s"String-$num"
        }
      } finally {
        fourLinesSource.close()
      }
    }

    "deal with potentially infinite stream properly" in {
      val infiniteSource = Source.fromIterable {
        new Iterable[Char] {
          override def iterator: Iterator[Char] = Iterator.continually('A')
        }
      }
      infiniteSource.slice(100000, 100010).mkString shouldEqual "AAAAAAAAAA"
    }
  }
}
