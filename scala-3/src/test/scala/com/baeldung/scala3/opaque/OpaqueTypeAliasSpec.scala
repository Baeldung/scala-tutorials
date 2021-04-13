package com.baeldung.scala3.opaque

import com.baeldung.scala3.opaque.types.{NoOfOscarsWon, RunningTimeInMin, Year}

class OpaqueTypeAliasSpec extends munit.FunSuite {

  test("successfully create and get values for opaque types") {
    val movie = Movie("Star Trek", Year(2009), RunningTimeInMin(127), NoOfOscarsWon(1))
    assert(clue(movie.name) == "Star Trek")
    assert(clue(movie.year.value) == 2009)
    assert(clue(movie.runningTime.value) == 127)
    assert(clue(movie.noOfOscarsWon.value) == 1)
  }

  test("apply an integer to opaque type and get the value back as int") {
    val year: Year = Year(2000)
    assert(clue(year.value) == 2000)
  }

  test("lift a value to opaque type safely") {
    val year: Option[Year] = Year.safe(2000)
    assert(clue(year.nonEmpty) )
  }

  test("do not build opaque type if the value is invalid") {
    val year:Option[Year] = Year.safe(100)
    assert(clue(year.isEmpty))
  }

  test("build Movie instance with valid values") {
    val spaceOdyssey = for {
      year <- Year.safe(1968)
      runningTime <- RunningTimeInMin.safe(149)
      noOfOscars <- NoOfOscarsWon.safe(1)
    }yield Movie("2001: A Space Odyssey", year, runningTime, noOfOscars)

    assert(clue(spaceOdyssey.isDefined))
    assert(clue(spaceOdyssey.get.runningTime.value) == 149)
  }

  test("return empty movie if an opaque type is having invalid value") {
    val spaceOdyssey = for {
      year <- Year.safe(1968)
      runningTime <- RunningTimeInMin.safe(-11)
      noOfOscars <- NoOfOscarsWon.safe(1)
    }yield Movie("2001: A Space Odyssey", year, runningTime, noOfOscars)

    assert(clue(spaceOdyssey.isEmpty))
  }

}
