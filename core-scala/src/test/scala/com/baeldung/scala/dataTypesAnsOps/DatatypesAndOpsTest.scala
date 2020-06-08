package com.baeldung.scala.dataTypesAnsOps

import org.scalatest.{Matchers, WordSpec}

class DatatypesAndOpsTest extends WordSpec with Matchers  {

  "Basic  DataTypes of Scala" should {
    "infer the type from assignment " in {
      val b1: Byte = 0
      val b2: Byte = 127
      val c1: Char = 'A'
      val c2: Char = 127
      val c3: Char = 126
      val c4: Char = 169

      b1.isInstanceOf[Byte]       should be (true)
      b1                          should be (0)
      b2.isInstanceOf[Byte]       should be (true)
      b2                          should be (127)
      c1                          should be ('A')
      c1.isInstanceOf[Char]       should be (true)
      c3                          should be ('~')
      c4                          should be ('©')

      val trueVal = true
      val falseVal = !true
      val falseValOtherWay = !true

      trueVal            should be (true)
      falseVal           should be (false)
      falseValOtherWay   should be (false)
    }

    "convert to and from Int types, following the conversion rules " in {

       // The line below will not compile!
       // val justAByte: Byte =  129  // Beyond the range of values allowed for Byte

       val aByteSizedInteger = 127
       val byteFromInt = aByteSizedInteger.toByte
       val aNonByteInteger = 128
       val wrappedByte = aNonByteInteger.toByte // wrapped around
       val anotherNonByteInteger = 129
       val wrappedByteAgain = anotherNonByteInteger.toByte // wrapped around
       val convFromInt: Byte = 129.toByte
       val converedBackToInt = convFromInt.toInt


      byteFromInt.isInstanceOf[Byte]              should be (true)
      byteFromInt                                 should be (127)
      wrappedByte                                 should be (-128)
      wrappedByteAgain                            should be (-127)
      converedBackToInt                           should be (-127)

    }

    "convert to and from Long types, following the conversion rules " in {

       val l     = 65536L   // Long literal
       val i1    = 65536    // No qualifier, therefore Int
       val i2    = l        // i2 is a Long because RHS is a Long

       // The followinig line will not compile because Long, a larger size is being cast into Into, a smaller size
       // val i2: Int = l    // the target variable's type is explicit: an Int

      val i3 = l.toInt     // Conversion helper function works
      val s3 = l.toShort

       l           should  be (65536L)
       i1          should  be (65536)
       i2          should  equal(i1.toLong)
       i3          should  equal(i1)
       s3          should  be (0)   // Oops! A Short is too small for a large value as 65536

    }

    "be assignable to primary superclasses for value and reference " in {

      class Article(heading: String, noOfLines: Int)

       val i: Int = 1234
       val d = 1.2E7
       val b = false
       val a1: AnyVal = i       // casting an Int to AnyVal
       val a2: AnyVal = d       // casting a Double to an AnyVal
       val a3: AnyVal = b       // casting a Boolean to an AnyVal
       val article = new Article("Baeldung",2000) // an application class
       val author = "Eugene"

       val parentClass1: AnyRef = article       // casting an User-Defined Object to an AnyRef
       val parentClass2: AnyRef = author        // casting a String to an AnyRef

    }

  }

}
