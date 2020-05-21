package com.baeldung.scala.abstractclassesvstraits
package traits

import org.scalatest.FlatSpec

class TraitsTest extends FlatSpec {
  trait BlueStadiumT extends FootballStadium {
    override protected val stadiumColour: String = "Blue"
  }

  trait GreenStadiumT extends FootballStadium {
    override protected val stadiumColour: String = "Green"
  }

  "A class that inherits from multiple traits" should "compile" in {
    assertCompiles(
      "class YelloStadium(name : String, colour : String, capacity : Int) " +
        "extends BlueStadiumT with GreenStadiumT " +
        "{ override protected val stadiumName: String = name ; override protected val stadiumCapacity: Int = capacity }"
    )

  }

  "A class that extends a trait " should "return correct information " in {
    case class PurpleStadium(name : String, capacity : Int, colour : String) extends FootballStadium {
      override protected val stadiumName: String = name
      override protected val stadiumColour: String = colour
    }

    val purpleStadium = PurpleStadium("Purple Rangers", 100000,"Purple")
    val supporter = "Tom"

    assert(purpleStadium.watchFootball(supporter).contentEquals("Tom is watching football at Purple Rangers stadium"))
    assert(purpleStadium.capacity == 100000)
    assert(purpleStadium.colour == "Purple")

  }

}
