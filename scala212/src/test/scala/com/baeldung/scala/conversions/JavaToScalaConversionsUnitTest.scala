package com.baeldung.scala.conversions

import com.baedung.scala.conversions.JavaApi

import scala.collection.convert.{DecorateAsJava, DecorateAsScala}
import org.scalatest.FlatSpec
import org.scalatest.matchers.should.Matchers
class JavaToScalaConversionsUnitTest extends FlatSpec with Matchers
  with DecorateAsScala with DecorateAsJava {

  "Standard conversions" should "convert from Java Iterators and back" in {
    val api = new JavaApi
    val javaList = api.getOneToFive
    val incremented = javaList.asScala.map(_ + 1).map(Integer.valueOf(_))

    assert(api.iteratorToString(incremented.asJava) == "[2, 3, 4, 5, 6]")
  }

  "Standard conversions" should "support Java's lists" in {
    val api = new JavaApi
    val javaList = api.getNames

    val scalaList = for (name <- javaList.asScala) yield s"Hello ${name}"
    val withExclamation = api.addExclamation(scalaList.asJava)

    assert(withExclamation.toString == "[Hello Oscar!, Hello Helga!, Hello Faust!]")
    assert(!(withExclamation eq javaList))
  }

  "Standard conversions" should "support Java's mutable lists" in {
    val api = new JavaApi
    val javaList = api.getNames

    val scalaList = javaList.asScala
    for (ix <- 0 until scalaList.size) {
      scalaList(ix) = s"Hi ${scalaList(ix)}"
    }
    val withExclamation = api.addExclamation(scalaList.asJava)

    assert(withExclamation.toString == "[Hi Oscar!, Hi Helga!, Hi Faust!]")
    assert(withExclamation eq javaList)
  }

  "Java properties" should "be converted to a Scala Map" in {
    val api = new JavaApi
    val javaProps = api.getConfig

    assert(javaProps.asScala == Map("name" -> "Oscar", "level" -> "hard"))
  }
}
