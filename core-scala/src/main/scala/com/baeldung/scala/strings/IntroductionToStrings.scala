package com.baeldung.scala.strings

import java.time.LocalDateTime

object IntroductionToStrings extends App {

  object Overview {

    """
      |In this tutorial, 
      |we are going to visit the ways Scala makes working 
      |with String an elegant and amicable task.
      |""".stripMargin

  }

  object StringInitialization {

    // Here we see how to initialize a single line String
    val singleLine = "This is a single line String"

    // And a multi line String
    val multiline =
      """
        | And this:
        |   is a multiline String
        |""".stripMargin

    // If we don't use stripMargin we would write like this:
    val uglyMultiline =
      """
 And this:
   is a multiline String
"""
    assert(multiline == uglyMultiline)

  }

  object StringInterpolation {

    val name = "Mark"
    val helloExample = s"Hello, $name"

    // we can make small computations inside a String:
    s"The hour is: ${LocalDateTime.now}"
    // this may become unreadable:

    s"Here we count to ten: ${(1 to 10) mkString "\n"}"

    // so it is better to extract the code like this:
    val oneToTen = (1 to 10) mkString "\n"
    val countingExample = s"Here we count to ten: $oneToTen"

  }

  object StringFunctions {

    val mkString = Array(1, 2, 3) mkString ","

    val replace = "I like cooking, dogs, and kids." replace (",", "")

  }

}
