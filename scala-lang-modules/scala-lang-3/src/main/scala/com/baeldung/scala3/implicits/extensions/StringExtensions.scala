package com.baeldung.scala3.implicits.extensions

object StringExtensions {
  extension (str: String) {
    def toSnakeCase = {
      str.replaceAll("([A-Z])", "_" + "$1").toLowerCase
    }

    def isNumber = {
      str.matches("[0-9]+")
    }
  }
}

object GenericExtensions {
  extension [T](list: List[T]) {
    def getSecond = if (list.isEmpty) None else list.tail.headOption
  }

  extension [T: Numeric](a: T) {
    def add(b: T): T = {
      val numeric = summon[Numeric[T]]
      numeric.plus(a, b)
    }
  }

  extension [T](a: T)(using numeric: Numeric[T]) {
    def add2(b: T): T = {
      numeric.plus(a, b)
    }
  }
}
