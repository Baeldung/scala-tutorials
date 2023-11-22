package com.baeldung.scala3.macros.inline
object InlineMatch {
  inline def classify(value: Any) = {
    inline value match {
      case int: Int    => "Int"
      case str: String => "String"
    }
  }
  @main def matchMain = println(classify("hello"))
}
