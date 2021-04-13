package com.baeldung.scala3.opaque

import com.baeldung.scala3.opaque.types._

final case class Movie(name: String, year: Year, runningTime: RunningTimeInMin, noOfOscarsWon: NoOfOscarsWon)

case class Year(year:Int) extends AnyVal
case class RunningTimeInMin(runningTime:Int) extends AnyVal
case class NoOfOscarsWon(noOfOscarsWon:Int) extends AnyVal
final case class Movie(name: String, year: Year, runningTime: RunningTimeInMin, noOfOscarsWon: NoOfOscarsWon)

object types {

  opaque type Year = Int

  object Year {
    def apply(value: Int): Year = value

    def safe(value: Int): Option[Year] = if (value > 1900) Some(value) else None

    extension (year: Year) {
      def value: Int = year
    }
  }

  opaque type RunningTimeInMin = Int

  object RunningTimeInMin {
    def apply(value: Int): RunningTimeInMin = value

    def safe(value: Int): Option[RunningTimeInMin] = if (value > 10 && value < 300) Some(value) else None

    extension (time: RunningTimeInMin) {
      def value: Int = time
    }
  }

  opaque type NoOfOscarsWon = Int

  object NoOfOscarsWon {
    def apply(value: Int): NoOfOscarsWon = value

    def safe(value: Int): Option[NoOfOscarsWon] = if (value >= 0) Some(value) else None

    extension (oscars: NoOfOscarsWon) {
      def value: Int = oscars
    }
  }
}
