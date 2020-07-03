package com.baeldung.scala.callbynameandvalue

import com.baeldung.scala.callbynameandvalue.CallByNameCallByValue._
import org.scalatest.{FlatSpec, Matchers}

/**
 * @author vid2010
 */
class CallByNameCallByValueTest extends FlatSpec with Matchers {

  "A call by value" should "have evaluated before passing an argument" in {
    getTimeByVal(System.currentTimeMillis()) should be(System.currentTimeMillis())
  }

  "A call by name" should "have evaluated when an argument used inside the function's body" in {
    getTimeByName(System.currentTimeMillis()) should not be (System.currentTimeMillis())
  }

  "A call by name and call by value" should "have reduced to final value" in {
    addFirst(2 + 5, 7) should be(addFirst(7, 2 + 5))
  }

  "A call by value" should "have produce StackOverflowError when addFirst is invoked with an argument " +
    "infinite() as a call by value" in {
    assertThrows[StackOverflowError] {
      addFirst(infinite(), 4)
    }
  }

  "A call by name" should "have reduced to final value when addFirst is invoked with an argument infinite() " +
    "as a call by name" in {
    assert(addFirst(4, infinite()) == 8)
  }
}
