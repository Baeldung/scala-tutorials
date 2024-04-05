package com.baeldung.scala.bitset

import org.scalatest.Assertions
import org.scalatest.wordspec.AnyWordSpec

import scala.collection.immutable.BitSet

class BitSetTest extends AnyWordSpec with Assertions {

  "BitSet.apply" should {
    "create an empty instance" in {
      assert(BitSet() === BitSet.empty)
    }

    "create an non-empty instance when arguments are provided" in {
      assert(BitSet(2) === BitSet() + 2)
      assert(BitSet(2) === BitSet().incl(2))
    }
  }

  "BitSet.excl" should {
    "remove an element if present" in {
      assert(BitSet(3, 4).excl(3) === BitSet(4))
      assert(BitSet(4).excl(3) === BitSet(4))
    }
  }

  "BitSet.incl" should {
    "include an element if not present" in {
      assert(BitSet(3).incl(4) === BitSet(3, 4))
      assert(BitSet(3, 4).incl(3) === BitSet(3, 4))
    }
  }

  "BitSet.contains" should {
    "return true for existing elements" in {
      assert(BitSet(3, 4).contains(3))
    }

    "return false for non-existing elements" in {
      assert(!BitSet(3, 4).contains(12))
    }
  }

  "BitSet.diff" should {
    "return the difference of two BitSet instances" in {
      assert(BitSet(3, 4).diff(BitSet(2, 3)) == BitSet(4))
    }

    "return an empty BitSet for equal sets" in {
      assert(BitSet(3, 4).diff(BitSet(3, 4)) == BitSet.empty)
    }
  }

  "BitSet.concat" should {
    "return a BitSet containing the elements of both sets" in {
      assert(BitSet(3, 4).concat(BitSet(2, 3)) == BitSet(2, 3, 4))
    }
  }

  "BitSet.union" should {
    "return a BitSet containing the elements of both sets" in {
      assert(BitSet(3, 4).union(BitSet(2, 3)) == BitSet(2, 3, 4))
      assert(
        BitSet(3, 4).union(BitSet(2, 3)) == BitSet(3, 4).concat(BitSet(2, 3))
      )
    }
  }

  "BitSet.intersect" should {
    "return a BitSet containing only elements existing in both sets" in {
      assert(BitSet(3, 4).intersect(BitSet(2, 3)) == BitSet(3))
    }
  }

  "BitSet.xor" should {
    "return a BitSet containing only elements existing in either set1 or set2" in {
      assert(BitSet(3, 4).xor(BitSet(2, 3)) == BitSet(2, 4))
    }
  }

  "BitSet.toBitMask" should {
    "return an array containing the words that are stored internally" in {
      assert(BitSet(3, 4).toBitMask.sameElements(List(24)))
    }
  }

  "BitSet.fromBitMask" should {
    "return a BitSet containing internally as words the given arguments" in {
      assert(BitSet.fromBitMaskNoCopy(Array(25L)) == BitSet(0, 3, 4))
    }
  }

}
