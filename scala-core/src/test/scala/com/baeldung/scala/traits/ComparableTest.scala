package com.baeldung.scala.traits

import org.scalatest._
import matchers._

class ComarableSpec extends FlatSpec  {

  "Comparable trait" should "correctly compare its subclasses" in {
    case class Value(v: Int ) extends Comparable[Value] {
      def < (that: Value): Boolean =  v < that.v
    }
    assert(
      (Value(1) < Value(2)) && 
      (Value(1) <= Value(2)) && 
      (Value(1) <= Value(1)) 
    )
  }

  "Comparable abstract class " should "correctly compare its subclasses" in {
    case class Value(v: Int ) extends AComparable[Value] {
      def < (that: Value): Boolean =  v < that.v
    }
    assert(
      (Value(1) < Value(2)) && 
      (Value(1) <= Value(2)) && 
      (Value(1) <= Value(1)) 
    )
  }
}

