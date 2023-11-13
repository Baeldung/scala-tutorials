package com.baeldung.scala3.macros

import scala.quoted._

object OddEvenMacros {

  inline def oddEvenMacroInline(inline number: Int): String =
    number % 2 match {
      case 0 => "even"
      case _ => "odd"
    }

  inline def oddEvenMacroInlineConditional(inline number: Int): String =
    inline number % 2 match {
      case 0 => "even"
      case 1 => "odd"
    }

  inline def oddEvenMacro(inline number: Int): String =
    ${ oddEven('number) }

  def oddEven(n: Expr[Int])(using Quotes): Expr[String] = {
    import quotes.reflect.*
    val number = n.valueOrAbort
    println(n.asTerm.show)
    number % 2 match {
      case 0 => Expr("even")
      case _ => Expr("odd")
    }
  }

  inline def oddEvenMacroQuote(inline number: Int): String =
    ${ oddEvenQuotes('number) }

  def oddEvenQuotes(n: Expr[Int])(using Quotes): Expr[String] = '{
    $n % 2 match {
      case 0 => "even"
      case _ => "odd"
    }
  }

  transparent inline def oddEvenMacroTransparent(inline number: Int): "even" |
    "odd" = {
    inline number % 2 match {
      case 0 =>
        "even"
      case _ =>
        "odd"
    }

  }

}
