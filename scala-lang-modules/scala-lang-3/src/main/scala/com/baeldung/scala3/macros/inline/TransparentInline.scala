package com.baeldung.scala3.macros.inline
object TransparentInline {
  transparent inline def sum(a: Int, b: Int) = a + b
  val total: 30 = sum(10, 20) // The type is Literal Value 30 instead of Int
}
