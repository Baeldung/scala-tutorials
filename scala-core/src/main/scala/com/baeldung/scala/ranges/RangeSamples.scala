package com.baeldung.scala.ranges

/**
 * Created by yadu on 23/07/20
 */


object RangeSamples {

  val rangeIncl = Range.inclusive(1, 10) // [1,2,3,4,5,6,7,8,9,10]

  val rangeExcl = Range(1, 10) // [1,2,3,4,5,6,7,8,9]

  val rangeTo = 1 to 10 // Same as rangeIncl - [1,2,3,4,5,6,7,8,9,10]

  val rangeUntil = 1 until 10 // Same as rangeExcl - [1,2,3,4,5,6,7,8,9]

  val rangeOdd = 1 to 10 by 2 // [1,3,5,7,9]

  val rangeRevPositiveStep = 10 to 1 by 2 // empty range, since it is not possible to start at 10 and end at 1 using positive step

  val rangeNegStep = 10 to 1 by -2 // [10,8,6,4,2]

  val negativeNumRange = -1 to -5 by -1 // [-1,-2,-3,-4,-5]

  val reverseRange = rangeOdd.reverse // [9,7,5,3,1]

  val doubleRange = 1.0 to 2.0 by 0.2 // [1.0,1.2,1.4,1.6,1.8,2.0], but deprecated since 2.12.6 as decimal precision can go wrong sometimes

  val decimalRange = BigDecimal(1.0) to BigDecimal(2.0) by 0.2 // [1.0,1.2,1.4,1.6,1.8,2.0]

  val mappedRange = rangeOdd.map(_ * 2) // [2,6,10,14,18]

  rangeOdd.foreach(n => print(n)) // print values 1 3 5 7 9

  val take2 = rangeIncl.take(2) // [1,2]

  val drop5 = rangeIncl.drop(5) // [6,7,8,9,10]

  val rangeHead = rangeIncl.head // 1

  val rangeLast = rangeIncl.last // 10

  val size = rangeIncl.size // 10

  val sum = rangeIncl.sum // 55

  val toInclusive = rangeExcl.inclusive //convert range to inclusive

  val step = rangeExcl.step //get step of the range
}
