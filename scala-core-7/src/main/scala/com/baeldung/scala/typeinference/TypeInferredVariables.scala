package com.baeldung.scala.typeinference

object TypeInferredVariables extends App{

  val integerObj = 10
  val doubleObj = 10.01
  val charObj = 'a'
  val stringObj = "hello"
  val booleanObj = true

  println(integerObj.getClass)  // prints int
  println(doubleObj.getClass)   // prints double
  println(charObj.getClass)     // prints char
  println(stringObj.getClass)   // prints java.lang.String
  println(booleanObj.getClass)  // prints boolean
}
