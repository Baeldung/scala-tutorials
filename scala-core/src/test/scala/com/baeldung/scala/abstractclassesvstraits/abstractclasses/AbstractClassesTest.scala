package com.baeldung.scala.abstractclassesvstraits
package abstractclasses

import org.scalatest.FlatSpec

class AbstractClassTest extends FlatSpec {

  abstract class BlueStadiumA(name : String, colour : String) extends FootballStadium(name,colour)
  abstract class GreenStadiumA(name : String, colour : String) extends FootballStadium(name,colour)

  "a class that inherits multiple abstract classes " should "not compile" in {
    assertDoesNotCompile(
      "class YellowStadium(name : String, capacity : Int, colour : String)" +
        """ extends BlueStadiumA("BlueStadium","Blue") with GreenStadiumA("GreenStadium","Green")""" +
        "{ override def getCapacity: Int = capacity } "
    )
  }

  "Extending an abstract class " should "return correct information " in {
    case class PurpleStadium(name : String, capacity : Int, colour : String) extends FootballStadium (name,colour){
      override def getCapacity: Int = capacity
    }
    val purpleStadium = PurpleStadium("Purple Rangers", 100000,"Purple")
    val supporter = "Tom"

    assert(purpleStadium.watchFootball(supporter).contentEquals("Tom is watching football at Purple Rangers stadium"))
    assert(purpleStadium.capacity == 100000)
    assert(purpleStadium.colour == "Purple")
  }
}

