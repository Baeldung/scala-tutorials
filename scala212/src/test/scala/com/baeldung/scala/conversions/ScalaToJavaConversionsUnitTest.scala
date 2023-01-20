package com.baeldung.scala.conversions

import com.baedung.scala.conversions.JavaApi
import org.scalatest.{FlatSpec, Matchers}

import scala.collection.convert.DecorateAsJava
class ScalaToJavaConversionsUnitTest extends FlatSpec with Matchers
  with DecorateAsJava {

  "A Scala Iterable" should "be passable as a parameter expecting a Java Collection" in {
    val api = new JavaApi

    val scalaIterable = Seq(1, 2, 3).toIterable
    assert(api.collectionSize(scalaIterable.asJavaCollection) == "Collection of size: 3")
  }

}
