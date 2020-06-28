package com.baeldung.scala.callbynameandvalue

import com.baeldung.scala.callbynameandvalue.CallByNameCallByValue._
import org.scalatest.{FlatSpec, Matchers}

/**
 * @author vid2010
 */
class CallByNameCallByValueTest extends FlatSpec with Matchers {

  "A call by value" should "have evaluated at argument passing time" in {
    getTimeByVal(System.currentTimeMillis()) should be(System.currentTimeMillis())
  }

  "A call by name" should "have evaluated when an argument used inside the function's body" in {
    getTimeByName(System.currentTimeMillis()) should not be (System.currentTimeMillis())
  }

  "A call by name and call by value" should "have reduced to final value" in {
    add(2 + 5, 7) should be(add(7, 2 + 5))
  }

}
