package com.baeldung.scala.macros

import scala.language.experimental.macros
import scala.reflect.macros.blackbox

object GenericMacros {

  def getType[T](obj: T): String = macro getTypeImplRef[T]

  def getTypeImplRef[T: c.WeakTypeTag](
    c: blackbox.Context
  )(obj: c.Expr[T]): c.universe.Tree = {
    import c.universe._

    val x = c.weakTypeTag[T].tpe.toString

    q"$x"
  }
}
