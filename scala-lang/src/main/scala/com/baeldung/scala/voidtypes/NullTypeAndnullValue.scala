package com.baeldung.scala.voidtypes

object NullTypeAndnullValue extends App{

  case class Car(make:String)

  //Initializes an instance of Car with null reference
  val nullRefCar:Car = null
  try{
    println(nullRefCar.make)
  }catch{
    case npe:NullPointerException => println("Null Pointer Error occurred: %s".format(npe))
  }

  //Initializes an instance of Car type with argument as null
  val nullMakeCar = Car(null)
  println(nullMakeCar.make)

  val nullValue:Null = null

  def nullArgFunction(nullArg:Null): Unit ={

  }
}
