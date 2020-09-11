package com.baeldung.scala.typecasts

class ValueTypeCasting {
  val byte: Byte = 0xF
  val shortInt: Short = byte // Safe to coerce or promote a byte to a short int

  // Following line will not compile as the Scala compiler
  // does not allow coercing a short int to a byte
  //val byte2: Byte = shortInt
}
