package com.baeldung.scala3.macros.inline
object InlineVal {
  @main def valMain: Unit = if (debugLogEnabled) { println("Hello world!") }
  inline val debugLogEnabled = true
}
