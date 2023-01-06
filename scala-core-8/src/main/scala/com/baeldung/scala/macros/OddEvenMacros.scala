package com.baeldung.scala.macros

import scala.language.experimental.macros
import scala.reflect.macros.blackbox

object OddEvenMacros {

  def defOddEvenMacro(number: Int): String = macro defMacroImplRef

  def defMacroImplRef(
    c: blackbox.Context
  )(number: c.Expr[Int]): c.Expr[String] = {
    import c.universe._

    val Literal(Constant(s_number: Int)) = number.tree

    val result = s_number % 2 match {
      case 0 => Literal(Constant("even"))
      case _ => Literal(Constant("odd"))
    }

    c.Expr[String](result)

  }

  def defOddEvenMacroReify(number: Int): String = macro defMacroImplReifyRef

  def defMacroImplReifyRef(
    c: blackbox.Context
  )(number: c.Expr[Int]): c.Expr[String] = {
    import c.universe._

    reify {
      number.splice % 2 match {
        case 0 => "even"
        case _ => "odd"
      }
    }

  }

  def oddEvenMacroBundle(number: Int): String = macro Bundle.macroBundleImpRef
}

class Bundle(val c: blackbox.Context) {
  import c.universe._

  def macroBundleImpRef(number: c.Expr[Int]): Tree =
    q"""
         $number % 2 match {
           case 0 => "even"
           case _ => "odd"
         }"""
}
