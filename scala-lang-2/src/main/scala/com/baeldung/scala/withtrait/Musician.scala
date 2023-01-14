package com.baeldung.scala.withtrait

trait Musician {
  val instrument, genre: String
  val repertoire: List[String]

  def tuneInstrument(): String =
    s"I'm tuning my $instrument"

  def playSong(song: String): String =
    s"I'm playing the beautiful song $song"
}
