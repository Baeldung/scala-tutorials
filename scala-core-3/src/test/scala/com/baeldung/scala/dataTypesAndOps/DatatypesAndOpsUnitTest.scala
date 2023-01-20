package com.baeldung.scala.dataTypesAndOps

import org.scalatest.{Matchers, WordSpec}

class DatatypesAndOpsUnitTest extends WordSpec with Matchers {

  "Basic  DataTypes of Scala" should {
    "infer the type from assignment " in {
      val b1: Byte = 0
      val b2: Byte = 127
      val c1: Char = 'A'
      val c2: Char = 127
      val c3: Char = 126
      val c4: Char = 169

      // val b3: Byte = 169 (will not compile)

      b1.isInstanceOf[Byte] should be(true)
      b1 should be(0)
      b2.isInstanceOf[Byte] should be(true)
      b2 should be(127)
      c1 should be('A')
      c1.isInstanceOf[Char] should be(true)
      c3 should be('~')
      c4 should be('©')

      val i1: Int = 65535
      val i2: Int = 65536
      val l1: Long = 65536

      val i3: Int = 32768
      val s1: Short = 32767
      // val s2: Short = 32768 (will not compile)

      // Types are inferred from the values assigned
      val i5 = 1234 // inferred as an Int
      val i6 = 32767 // inferred as an Int
      val s2: Short = 32767 // qualify with intended type
      val i7 = 127 // inferred as an Int
      val b3: Byte = 127 // qualify with intended type
      val i8 = 0xafbf // HEX value, inferred as an Int
      val c5 = 234 // inferred as an Int
      val c6 = '®' // inferred as a Char
      val l4 = 1234L // 'L' signifies a Long literal
      val l3 = 0xcafebabeL // 'L' signifies a Long literal, even for a HEX value

      val trueVal = true
      val falseValOtherWay = !true

      trueVal should be(true)
      falseValOtherWay should be(false)

      val f1 = 12.05f // 'f' signifies that it is a Float
      val d1 = 12.3495067 // inferred as a Double
      val d2 = 12.3495067d // 'D' signfies a Double, but is optional

      val name = "Diego Armando Maradona"
      val nameWithQuote = "Kerry O\'keffey"
      val ageAsString = "100"
    }

    "convert to and from Int types, following the conversion rules " in {

      // The line below will not compile!
      // val justAByte: Byte =  129  // Beyond the range of values allowed for Byte, will not compile

      val aByteSizedInteger = 127
      val byteFromInt = aByteSizedInteger.toByte
      val aNonByteInteger = 128
      val wrappedByte = aNonByteInteger.toByte // wrapped around
      val anotherNonByteInteger = 129
      val wrappedByteAgain = anotherNonByteInteger.toByte // wrapped around
      val convFromInt: Byte = 129.toByte
      val converedBackToInt = convFromInt.toInt

      byteFromInt.isInstanceOf[Byte] should be(true)
      byteFromInt should be(127)
      wrappedByte should be(-128)
      wrappedByteAgain should be(-127)
      converedBackToInt should be(-127)

      val c4: Char = '®'
      val symbolToInt = c4.toInt // from a Char
      val stringToInt = "100".toInt

    }

    "convert to and from Long types, following the conversion rules " in {

      val l = 65536L // Long literal
      val i1 = 65536 // No qualifier, therefore Int
      val i2 = l // i2 is a Long because RHS is a Long

      // The followinig line will not compile because Long, a larger size is being cast into Into, a smaller size
      // val i2: Int = l    // the target variable's type is explicit: an Int

      val i3 = l.toInt // Conversion helper function works
      val s3 = l.toShort

      l should be(65536L)
      i1 should be(65536)
      i2 should equal(i1.toLong)
      i3 should equal(i1)
      s3 should be(0) // Oops! A Short is too small for a large value as 65536

      val i10 = 127 // Integer 127
      val i11 = 128 // Integer 128
      i11.isValidByte should be(false)
      i10.isValidByte should be(true)

      val i31 = 65536
      i31.isValidShort should be(false)

      val d3 = 2e31
      d3.isValidInt should be(false)

    }

    "be assignable to primary superclasses for value and reference " in {

      class Article(heading: String, noOfLines: Int)

      val i: Int = 1234
      val d = 1.2e7
      val b = false
      val a1: AnyVal = i // casting an Int to AnyVal
      val a2: AnyVal = d // casting a Double to an AnyVal
      val a3: AnyVal = b // casting a Boolean to an AnyVal
      val article = new Article("Baeldung", 2000) // an application class
      val author = "Eugene"

      val parentClass1: AnyRef =
        article // casting an User-Defined Object to an AnyRef
      val parentClass2: AnyRef = author // casting a String to an AnyRef

    }

  }

}
