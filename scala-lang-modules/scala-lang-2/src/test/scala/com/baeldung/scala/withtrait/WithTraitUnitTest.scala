package com.baeldung.scala.withtrait

import org.scalatest.wordspec.AnyWordSpec

import java.time.LocalDate

class WithTraitUnitTest extends AnyWordSpec {

  import WithTraitSpec._

  "A person should" should {
    "know how to breathe and say their name" in {
      assertResult("I'm Pat, alive and kicking")(pat.breathe())
    }
  }

  "A musician" should {
    "know their name, as a person" in {
      assertResult("I'm Mary, alive and kicking")(mary.breathe())
    }
    "tune their instrument" in {
      assertResult("I'm tuning my guitar")(mary.tuneInstrument())
    }
  }

  "A politician" should {
    "know their name, as a person" in {
      assertResult("I'm Prudence, alive and kicking")(prudence.breathe())
    }
    "say an important speech, as a politician" in {
      assertResult("I'm saying an important speech: blah, blah, blah...")(
        prudence.saySpeech("blah, blah, blah...")
      )
    }
  }

  "A musician/politician" should {
    "know their name, as a person" in {
      assertResult("I'm Giorgio, alive and kicking")(giorgio.breathe())
    }
    "cast a vote, as a politician" in {
      assertResult("I'm voting")(giorgio.vote())
    }
    "play a song" in {
      assertResult("I'm playing the beautiful song #1")(giorgio.playSong("#1"))
    }
  }
}

object WithTraitSpec {
  val pat: Person =
    new Person("Pat", "123 Main St.", LocalDate.of(1933, 10, 11))

  val mary: Person with Musician =
    new Person("Mary", "456 Second St.", LocalDate.of(1982, 9, 9))
      with Musician {
      override val instrument: String = "guitar"
    }

  val prudence: Person with Politician =
    new Person("Prudence", "789 Third St.", LocalDate.of(1972, 6, 3))
      with Politician

  val giorgio: Person with Politician with Musician =
    new Person("Giorgio", "121 Fourth St.", LocalDate.of(1980, 2, 19))
      with Politician
      with Musician {
      override val instrument: String = "flute"
    }

  val ellie: Animal with Musician =
    new Animal("Ellie", "elephant") with Musician {
      override val instrument: String = "trombone"
    }

  val vasily: Animal with Politician =
    new Animal("Vasily", "monkey") with Politician
}
