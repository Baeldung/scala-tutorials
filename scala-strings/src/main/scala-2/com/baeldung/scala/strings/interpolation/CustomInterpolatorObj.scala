package com.baeldung.scala.strings.interpolation

object CustomInterpolatorObj {
  implicit class CustomInterpolator(val sc: StringContext) extends AnyVal {

    def custom(args: Any*): String = {
      val stringContextIterator = sc.parts.iterator
      val argsIterator = args.iterator

      val sb = new java.lang.StringBuilder(stringContextIterator.next())

      while (argsIterator.hasNext) {
        sb.append(argsIterator.next().toString)
        sb.append(stringContextIterator.next())
      }
      sb.toString.toUpperCase()
    }
  }
}
