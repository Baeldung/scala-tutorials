package com.baeldung.scala.monocle.scalaz.principles

import com.baeldung.scala.monocle.scalaz.principles.ScalazPrinciplesExamples._
import org.scalatest._

class ScalazPrinciplesExamplesUnitTest extends FlatSpec{

  it should "sort ints and strings" in {
    val integers = List(3, 2, 6, 5, 4, 1)
    val strings = List("c", "b", "f", "e", "d", "a")

    assertResult(expected = List(1, 2, 3, 4, 5, 6))(integers.sorted)
    assertResult(expected = List("a", "b", "c", "d", "e", "f"))(strings.sorted)
  }

  it should "sort custom types" in {
    val wrappedInts = List(IntWrapper(3), IntWrapper(2), IntWrapper(1))

    assertResult(expected = List(IntWrapper(1), IntWrapper(2), IntWrapper(3)))(wrappedInts.sorted)
  }

  "Doubler" should "work on any supported container type" in {
    val intList: List[Int] = List(1, 2, 3)
    val intOpt: Option[Int] = Some(5)
    val strList: List[String] = List("a", "b", "c")

    assertResult(expected = List(2, 4, 6))(actual = doubleIt(intList))
    assertResult(expected = Some(10))(actual = doubleIt(intOpt))
    assertResult(expected = List("aa", "bb", "cc"))(actual = doubleIt(strList))
  }
}
