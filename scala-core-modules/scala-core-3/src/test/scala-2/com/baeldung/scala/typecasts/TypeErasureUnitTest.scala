package com.baeldung.scala.typecasts

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class TypeErasureUnitTest extends AnyWordSpec with Matchers {

  "Generic types" should {
    "be discarded" in {

      val l1 = TypeErasure.convertValuesToList[Int](1, 2, 3)
      assert(l1.isInstanceOf[List[Int]])
      assert(l1.isInstanceOf[List[String]])

      val l2 = TypeErasure.convertValuesToList[String]("a", "b", "c")
      assert(l2.isInstanceOf[List[Int]])
      assert(l2.isInstanceOf[List[String]])
    }
  }

  "Casting" should {
    "be safe to cast T2 to T1" in {
      import TypeErasure._

      val t2: T2 = new T2
      val t1: T1 = t2.asInstanceOf[T1]
      assert(t2.isInstanceOf[T1])
    }

    "fail to cast T3 to T1" in {
      import TypeErasure._

      val t3 = new T3
      assert(!t3.isInstanceOf[T1])
    }
  }

  "Type ascriptions" should {
    "work with varargs" in {
      def varargFn(str: String*) = str.length
      val input = Seq("Hello", "World")
      assert(varargFn(input: _*) == 2)
    }
  }
}
