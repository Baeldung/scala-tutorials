package com.baeldung.scala.structuraltypes

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

import scala.io.Source
import scala.jdk.CollectionConverters._

class ResourceClosingUnitTest extends AnyFlatSpec with Matchers with ResourceClosing {
  "Scala Sources" should "be closed" in {
     val file = Source.fromResource("animals")
     using(file) {
       () =>
         for(line <- file.getLines) {
           println(line)
         }
     }
  }

  "Java files" should "be closed" in {
    import java.io.{BufferedReader, File, FileReader}

    val resource = getClass.getClassLoader.getResource("animals")
    val file = new FileReader(new File(resource.toURI()))
    using(file) {
      () =>
        val reader = new BufferedReader(file)
        for(line <- reader.lines().iterator().asScala) {
          println(line)
        }
    }
  }
}
