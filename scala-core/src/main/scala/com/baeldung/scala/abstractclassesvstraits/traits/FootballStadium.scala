package com.baeldung.scala.abstractclassesvstraits
package traits

private[traits] trait FootballStadium {
  protected val stadiumName : String
  protected val stadiumColour : String

  def watchFootball(watcher : String) : String = {
    s"$watcher is watching football at $stadiumName stadium"
  }
}

class BlueStadium(name : String, capacity : Int,colour : String) extends FootballStadium {
  override protected val stadiumColour: String = colour

  override protected val stadiumName: String = name

  def getWatcherInformation(watcher : String) : String = watchFootball(watcher)
}

class GreenStadium(name : String, capacity : Int,colour : String) extends FootballStadium {
  override protected val stadiumColour: String = colour

  override protected val stadiumName: String = name

  def getWatcherInformation(watcher : String) : String = watchFootball(watcher)
}

