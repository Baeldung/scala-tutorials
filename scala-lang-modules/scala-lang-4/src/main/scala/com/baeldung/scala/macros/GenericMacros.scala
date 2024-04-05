package com.baeldung.scala.macros

import scala.quoted.*
import scala.reflect.ClassTag

object GenericMacros {

  inline def getTypeMacro[T](obj: T): String = ${ getType('obj) }

  def getType[T](
    obj: Expr[T]
  )(using t: Type[T])(using Quotes): Expr[String] = '{
    val o: t.Underlying = $obj
    o.getClass.getSimpleName
  }
}
