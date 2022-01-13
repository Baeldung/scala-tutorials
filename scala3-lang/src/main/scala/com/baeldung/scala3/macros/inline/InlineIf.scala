package com.baeldung.scala3.macros.inline
object InlineIf {
  inline def hello: Unit =
    inline if (debugLogEnabled) {
      println("debug is enabled")
    } else {
      println("debug is disabled")
    }

  inline val debugLogEnabled = true

  @main def inlineIfMain = {
    hello
  }
}
