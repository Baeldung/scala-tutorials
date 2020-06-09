package com.baeldung.scala.strings

object CustomInterpolatorObj {
  implicit class CustomInterpolator(val sc: StringContext) extends AnyVal {

    def custom(args: Any*): String = {
      // implement your custom string interpolation
      sc.parts.mkString.replace(" ", "") + args.head.toString()
    }
  }
}
