package com.baeldung.scala.lazylists

import org.scalatest.wordspec.AnyWordSpec

class SimpleLazyListSpec extends AnyWordSpec {
  "A list" should {
    "Allow the creation of a one Element list" in {
      assertResult(5)((5 :: SLNil).head)
    }
    "A Simple Lazy List can represent a Fibonacci sequence without throwing a Stack Overflow" in {
      def fibonacci(current: Int = 0, next: Int = 1): SimpleLazyList[Int] =
        #:@:(current, fibonacci(next, current + next))
      assertResult(8)(fibonacci().!!(7))
    }
    "The head of the Simple Lazy List is strict" in {
      var side = "No side effect"
      val list = #:@:(
        {
          side = "Side effect"
          side
        },
        SLNil
      )
      assertResult("No side effect")(side)
      assertResult("Side effect")(list.head)
      assertResult("Side effect")(side)
    }
    "The head of a Scala Lazy List is lazy" in {
      var side = "No side effect"
      val list = {
        side = "Side effect"
        side
      } #:: LazyList.empty
      assertResult("No side effect")(side)
      list.map(x => {
        side = "Another side effect"
        x.toUpperCase
      })
      assertResult("No side effect")(side)
      list.filter(x => x.startsWith("Side"))
      assertResult("No side effect")(side)
      println(list.head)
      assertResult("Side effect")(side)
    }
  }
}
