package com.baeldung.scala.conditionaltests

import org.scalatest.{Assertion, Ignore, Tag}
import org.scalatest.wordspec.AnyWordSpec

object ServiceChecker {
  def isServiceAvailable: Boolean = false
}

object RequiresAvailableService extends Tag("RequiresAvailableService")
object RequiresAvailableServiceConditional
  extends Tag(
    if (ServiceChecker.isServiceAvailable) "" else classOf[Ignore].getName
  )

object AlwaysIgnoreTest extends Tag("org.scalatest.Ignore")

class ConditionalUnitTest extends AnyWordSpec {

  private def skipTestIfServerUnavailable(test: => Assertion): Assertion = {
    if (ServiceChecker.isServiceAvailable) {
      test
    } else cancel("Not executing test since the service is not available")
  }

  "Conditional Tests" should {
    "run this test only if the service is available by checking tag" taggedAs (RequiresAvailableService) in {
      succeed
    }

    "run this test anyways" in {
      succeed
    }

    "run this test using the conditional if-else tag if service is available" taggedAs (RequiresAvailableServiceConditional) in {
      succeed
    }

    "run this test using simple if-else condition" in {
      if (!ServiceChecker.isServiceAvailable) {
        cancel("excluding this test due to service not available")
      }
      succeed
    }

    "run this test based on assume" in {
      assume(ServiceChecker.isServiceAvailable)
      succeed
    }

    "run this test using skipTestIfServerUnavailable method" in skipTestIfServerUnavailable {
      succeed
    }

    "always ignore this test since Ignore name is used in Tag" taggedAs (AlwaysIgnoreTest) in {
      fail("this fails, but it should never reaches here due to the tag")
    }

    "ignore this test using the ignore keyword conditionally" ignore {
      fail()
    }
  }

}
