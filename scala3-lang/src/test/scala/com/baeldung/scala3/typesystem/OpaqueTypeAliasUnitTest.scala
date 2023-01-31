package com.baeldung.scala3.typesystem

import com.baeldung.scala3.typesystem.types.*
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

import java.time.LocalDate

class OpaqueTypeAliasUnitTest extends AnyWordSpec with Matchers {

  "Opaque type alias test" should {
    "successfully create and get values for opaque types" in {
      val movie = Movie("Star Trek", Year(2009), RunningTimeInMin(127), NoOfOscarsWon(1))
      movie.name shouldBe "Star Trek"
      movie.year.value shouldBe 2009
      movie.runningTime.value shouldBe 127
      movie.noOfOscarsWon.value shouldBe 1
    }

    "apply an integer to opaque type and get the value back as int" in {
      val year: Year = Year(2000)
      year.value shouldBe 2000
    }

    "lift a value to opaque type safely" in {
      val year: Option[Year] = Year.safe(2000)
      year.nonEmpty shouldBe true
    }

    "do not build opaque type if the value is invalid" in {
      val year: Option[Year] = Year.safe(100)
      year.isEmpty shouldBe true
    }

    "build Movie instance with valid values" in {
      val spaceOdyssey = for {
        year <- Year.safe(1968)
        runningTime <- RunningTimeInMin.safe(149)
        noOfOscars <- NoOfOscarsWon.safe(1)
      } yield Movie("2001: A Space Odyssey", year, runningTime, noOfOscars)

      spaceOdyssey.isDefined shouldBe true
      spaceOdyssey.get.runningTime.value shouldBe 149
    }

    "return empty movie if an opaque type is having invalid value" in {
      val spaceOdyssey = for {
        year <- Year.safe(1968)
        runningTime <- RunningTimeInMin.safe(-11)
        noOfOscars <- NoOfOscarsWon.safe(1)
      } yield Movie("2001: A Space Odyssey", year, runningTime, noOfOscars)

      spaceOdyssey.isEmpty shouldBe true
    }

    "opaque type with context bound" in {
      val date = LocalDate.parse("2021-04-20")
      val releaseDate = ReleaseDate(date)
      releaseDate shouldBe date
      releaseDate.getYear() shouldBe date.getYear
      val safeReleaseDate = ReleaseDate.safeParse("2021-04-20")
      safeReleaseDate.isDefined shouldBe true
      safeReleaseDate.get.toStr shouldBe "2021-04-20"
    }

    "opaque type with context bound using another opaque type" in {
      val date = LocalDate.parse("2021-04-20")
      val netflixReleaseDate = NetflixReleaseDate(date)
      netflixReleaseDate shouldBe date
      val releaseDate: ReleaseDate = netflixReleaseDate
      netflixReleaseDate.toStr shouldBe "2021-04-20"
      //NetflixReleaseDate.safeParse("2021-01-01") -- This will not compile even though NetflixReleaseDate has context bound
    }

  }
}
