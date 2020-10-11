package com.baeldung.scala.voidtypes

import org.scalatest.funsuite.AnyFunSuite

class NoneEmptyOptionTest extends AnyFunSuite{
  test("  "){
    val studentRegister:Map[Int,String] = Map(1 -> "Berlin", 2 -> "Nairobi")
    assert(NoneEmptyOption.getStudentName(studentRegister, 1) == Some("Berlin"))
    assert(NoneEmptyOption.getStudentName(studentRegister, 0) == None)
  }
}
