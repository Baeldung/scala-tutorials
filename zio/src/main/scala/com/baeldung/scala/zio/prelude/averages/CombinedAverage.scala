package com.baeldung.scala.zio.prelude.averages

import zio.prelude._

/** A combined average class that takes into consideration the need of carrying
  * the count of elements.
  *
  * @param sum
  *   average of elements
  * @param count
  *   number of elements
  */
case class CombinedAverage(sum: Double, count: Int)

object CombinedAverage {
  implicit val AvgAssociative: Associative[CombinedAverage] = {
    new Associative[CombinedAverage] {
      override def combine(
        l: => CombinedAverage,
        r: => CombinedAverage
      ): CombinedAverage = {
        val sum = l.sum * l.count + r.sum * r.count
        val count = l.count + r.count
        CombinedAverage(sum / count, count)
      }
    }
  }
}
