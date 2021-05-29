package com.baeldung.scala3.implicits

object WritingTypeclassInstances {

  trait Ord[T]:
    def compare(x: T, y: T): Int


  given Ord[Int] with
    override def compare(x: Int, y: Int) =
      if (x < y) -1 else if (x > y) +1 else 0
      
}
