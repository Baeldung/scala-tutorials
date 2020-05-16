package com.baeldung.scala.classvsobject

object ClassExamples {

  class Car (val manufacturer: String, var brand: String = "ty", var model: String) {
    var speed: Double = 0;
    var gear: Any = 0;
    var isOn: Boolean = false;

    def start(key_type: String): String = {
      s"Car started using the $key_type"
    }

    def selectGear(gear_number: Any): String = {
      gear = gear_number
      s"Gear has been changed to $gear_number"
    }

    def accelerate(rate: Double, seconds: Double): String = {
      speed += rate * seconds
      s"Car accelerates at $rate per second for $seconds seconds."
    }

    def brake(rate: Double, seconds: Double): String = {
      speed -= rate * seconds
      s"Car slows down at $rate per second for $seconds seconds."
    }

    def stop(): String = {
      speed = 0;
      gear = 0;
      isOn = false;
      "Car has stopped."
    }
  }

  class Toyota(transmission: String,brand: String, model: String) extends Car("Toyota", brand, model) {
    override def start(key_type: String): String = {
      if (isOn) {
        return "Car is already on."
      }
      if (transmission == "automatic") {
        isOn = true
        s"Car started using the $key_type"
      } else {
        isOn = true
        s"Please ensure you're holding down the clutch. Car started using the $key_type"
      }
    }
  }

  object Prediction {
    implicit class AgeFromName(name: String) {
      val r = new scala.util.Random
      def predictAge(): Int = 10 + r. nextInt(90)
    }
  }

  case class Artist(name: String, age: Int, country: String, role: String)

  class PlayList {
    var songs: List[Song] = Nil

    def addSong(song: Song): Unit = {
      songs = song :: songs
    }

    class Song(title: String, artist: Artist)
  }
}
