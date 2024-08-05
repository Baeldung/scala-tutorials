package com.baeldung.scala.tastyfiles

import scala.quoted.*

object PowerMacro:
  // The macro that unrolls the computation of powers and then generates the expression
  inline def showAsPowerTerm(inline x: Double, n: Int): String = ${
    showAsTermImpl('x, 'n)
  }

  // The actual implementation of the macro
  private def showAsTermImpl(x: Expr[Double], n: Expr[Int])(using
    Quotes
  ): Expr[String] =
    import quotes.reflect.*

    n.value match
      case Some(num) =>
        val powerExpr = unrolledPowerCode(x, num)
        Expr(
          powerExpr.asTerm.toString
        ) // Ensures that the asTerm method call is evaluated at compile-time
      case None =>
        '{ "Error: 'n' must be a known constant at compile time." }

  // Helper method to unroll the power computation
  def unrolledPowerCode(x: Expr[Double], n: Int)(using Quotes): Expr[Double] =
    if n == 0 then '{ 1.0 }
    else if n == 1 then x
    else '{ $x * ${ unrolledPowerCode(x, n - 1) } }
