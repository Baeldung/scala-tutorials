package com.baeldung.scala3.`enum`

object ColorExamples {
  object v1 {
    enum Color:
      case Red, Green, Blue
  }

  object v2 {
    enum Color extends java.lang.Enum[Color]:
      case Red, Green, Blue
  }

  object v3 {
    enum Color(code: String):
      case Red extends Color("#FF0000")
      case Green extends Color("#00FF00")
      case Blue extends Color("#0000FF")
  }

}
