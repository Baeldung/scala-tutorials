package com.baeldung.scala.structuraltypes

import org.scalatest.{FlatSpec, Matchers}
import scala.jdk.CollectionConverters._
import scala.io.Source

class ResourceClosingUnitTest extends FlatSpec with Matchers with ResourceClosing {
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
    import java.io.File
    import java.io.BufferedReader
    import java.io.FileReader

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
