package com.baeldung.scala.abstractclassesvstraits
package abstractclasses

private[abstractclasses] abstract class FootballStadium(stadiumName : String, stadiumColour : String ){
  def watchFootball(watcher : String) : String = {
    s"$watcher is watching football at $stadiumName stadium"
  }

  def getCapacity : Int
}

case class BlueStadium(name : String, capacity : Int, colour : String) extends FootballStadium(name,colour) {

  override def getCapacity: Int = capacity

  def getWatcherInformation(watcher : String) : String = watchFootball(watcher)

}

case class GreenStadium(name : String, capacity : Int,colour : String) extends FootballStadium(name,colour) {

  override def getCapacity: Int = capacity

  def getWatcherInformation(watcher: String): String = watchFootball(watcher)

}