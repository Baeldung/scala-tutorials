package com.baeldung.scala.operators

/** Code examples for Scala operators
  */
object ScalaOperators {

  // Operator & dot notation
  assert(1 + 2 == 3)
  assert(1.+(2) == 3)

  assert("Baeldung".charAt(0) == 'B')
  val char: Char = "Baeldung" charAt 0
  assert(char == 'B')

  assert("Baeldung".replace('g', 'G') == "BaeldunG")
  val str: String = "Baeldung" replace ('g', 'G')
  assert(str == "BaeldunG")

  // Unary operator notation
  assert(10.unary_- == -10)

  // Arithmetic operators
  assert(1 + 2 == 3)
  assert(3.1 - 1.0 == 2.1)
  assert(2 * 6 == 12)
  assert(15 / 6 == 2)
  assert(15 % 6 == 3)

  val num: Int = 10
  assert(-num == -10)
  assert(10 + -num == 0)

  // Relational operators
  assert(10 < 20 == true)
  assert(10 > 20 == false)
  assert(3.0 >= 2.5 == true)
  assert(3.0 <= 2.5 == false)
  assert(!true == false)

  // Logical operators
  assert(true || false == true)
  assert(true && false == false)

  def printTrue(): Boolean = {
    println("true");
    true
  }

  def printFalse(): Boolean = {
    println("false");
    false
  }

  val result1: Boolean = printFalse() && printTrue()
  val result2: Boolean = printTrue() && printFalse()
  val result3: Boolean = printFalse() & printTrue()
  assert(result1 == false)
  assert(result2 == false)
  assert(result3 == false)

  // Bitwise operators
  val bitwiseAndResult: Int = 2 & 4
  assert(bitwiseAndResult == 0)

  val bitwiseOrResult: Int = 2 | 4
  assert(bitwiseOrResult == 6)

  val bitwiseXorResult: Int = 2 ^ 4
  assert(bitwiseXorResult == 6)

  assert(~2 == -3)

  assert(2 << 2 == 8)
  assert(-8 >> 2 == -2)
  assert(-8 >>> 2 == 1073741822)

  // Equality operators
  assert(List(1, 2, 3) == List(1, 2, 3))
  assert(List() != List(1, 2, 3))

  // Operator precedence
  assert(2 + 3 * 4 == 14)
  assert(4 - 2 + 1 == 3)

  var value = 10
  value += 2 * 10
  assert(value == 30)

  // Operator associativity
  assert(2 * 3 == 6)
  assert(List(1, 2) ::: List(3) == List(1, 2, 3))
  assert(List(3).:::(List(1, 2)) == List(1, 2, 3))
}
