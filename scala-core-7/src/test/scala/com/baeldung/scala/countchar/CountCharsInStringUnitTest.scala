package com.baeldung.scala.countchar

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class CountCharsInStringUnitTest extends AnyFlatSpec with Matchers {
  "count with count()" should "correctly count char occurences in String" in {
    val string = "ThisIsAVeryLengthyString"
    val char = 'i';

    val count = CountCharsInString.countWithCount(string, char)

    count shouldBe 2
  }

  "count with recursion" should "correctly count char occurences in String" in {
    val string = "ThisIsAVeryLengthyStringy"
    val char = 'y';

    val count = CountCharsInString.countRecursive(string, char)

    count shouldBe 3
  }

  "count with groupBy" should "correctly count char occurences in String" in {
    val string = "ThisIsAVeryLengthyStringWegonnaCountIt"
    val char = 'g';

    val count = CountCharsInString.countWithGroupBy(string, char)

    count shouldBe 3
  }

  "count with filter" should "correctly count char occurences in String" in {
    val string = "ThisIsaVeryLongStringThatHasManyaS"
    val char = 'a';

    val count = CountCharsInString.countWithFilter(string, char)

    count shouldBe 5
  }


}
