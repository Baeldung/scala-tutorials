package com.baeldung.scala.scalatest

import org.scalatest.funspec.AnyFunSpec

class ListFunSpecUnitTest extends AnyFunSpec {

  describe("A List") {
    describe("when empty") {
      it("should have size 0") {
        assert(List.empty.size == 0)
      }

      it("should throw an IndexOutOfBoundsException when to access an element") {
        val emptyList = List()
        assertThrows[IndexOutOfBoundsException] {
          emptyList(1)
        }
      }
    }
  }
}
