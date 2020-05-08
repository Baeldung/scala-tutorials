package com.baeldung.scala.strings

import org.junit.Test
import org.scalatest.Matchers
import com.baeldung.scala.strings.AnIntroductionToStrings.Overview
import com.baeldung.scala.strings.AnIntroductionToStrings.StringInitialization
import com.baeldung.scala.strings.AnIntroductionToStrings.StringInterpolation
import com.baeldung.scala.strings.AnIntroductionToStrings.StringFunctions

class IntroductionToStringsSpec extends Matchers {

  @Test
  def givenAMultilineString_whenStripLined_thenReturnCleanerString() {
    (StringInitialization.uglyMultiline) should be(
      StringInitialization.multiline
    )
  }

  @Test
  def givenAString_whenInterpolated_thenReturnValueInside() {
    (StringInterpolation.helloExample) should be("Hello, Mark")

  }
  @Test
  def givenAString_whenInterpolatedWithCode_thenReturnResultInside() {
    (StringInterpolation.countingExample) should be("""Here we count to ten: 1
                                                      |2
                                                      |3
                                                      |4
                                                      |5
                                                      |6
                                                      |7
                                                      |8
                                                      |9
                                                      |10""".stripMargin)

  }

  @Test
  def givenAnArray_whenMkString_thenReturnString() {
    (StringFunctions.mkString) should be("1,2,3")
  }
  @Test
  def givenAString_whenReplaceCommasWithEmptySpace_thenReturnNoCommas() {
    (StringFunctions.replace) should not contain (",")
  }

}
