package com.baeldung.scala.voidtypes

object UnitReturnType extends App{

  def functionReturnUnit:Unit = {
    """
      do something, don't return anything
    """
  }
  println("result of function returning Unit: %s".format(functionReturnUnit))

  def functionReturnImplicitUnit{
    """
      do something, don't return anything
    """
  }
  println("result of function returning implicit Unit: %s".format(functionReturnImplicitUnit))
}
