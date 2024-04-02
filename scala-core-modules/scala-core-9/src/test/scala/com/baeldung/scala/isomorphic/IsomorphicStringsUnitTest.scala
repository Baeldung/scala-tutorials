package com.baeldung.scala.isomorphic

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should
import com.baeldung.scala.isomorphic.IsomorphicStringsChecker.checkIsomorphicBothWays
import org.scalatest.matchers.should.Matchers
import org.scalatest.prop.TableDrivenPropertyChecks
class IsomorphicStringsUnitTest
  extends AnyFlatSpec
  with TableDrivenPropertyChecks
  with Matchers {
  private val isomorphicStringChecker = Seq(
    ("isomorphicChecker", checkIsomorphicBothWays)
  )

  private val table = Table(
    ("str1", "str2", "Isomorphic"),
    ("aab", "xxy", true),
    ("aab", "xxyz", false),
    ("aaba", "xxyz", false),
    ("ab", "xxyz", false),
    ("aabac", "xxyz", false),
    ("aab", "xxb", true),
    (" ", "x", true),
    ("", "x", false),
    ("xxm", "aab", true)
  )
  isomorphicStringChecker.foreach { (name, fn) =>
    it should s"Check Isomorphic two String using $name" in {
      forAll(table) { (str1, str2, expectedResult) =>
        fn(str1, str2) shouldBe expectedResult
      }
    }
  }
}
