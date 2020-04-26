package com.baeldung.scala.abstractclasstrait.demo

trait AnimalTrait {
  var animalNumber : Int
  val groupNumber : Int
  //set animal number
  def setAnimalNumber(x : Int){
    animalNumber = x
  }

  def makeNoise
}
