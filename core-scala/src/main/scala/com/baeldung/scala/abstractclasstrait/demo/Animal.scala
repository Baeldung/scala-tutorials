package com.baeldung.scala.abstractclasstrait.demo

abstract class Animal(animalKind : String) {
  var animalNumber : Int
  val groupNumber : Int
  //set animal number
  def setAnimalNumber(x : Int){
    animalNumber = x
  }

   def makeNoise
}



