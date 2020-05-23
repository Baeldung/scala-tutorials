package com.baeldung.scala.classvsobject

object ClassExamples {

  val constA = "A"
  val constB = 4
  class Abc(var a: String, var b: Int) {
    def this(a: String) {
      this(a, constB)
      this.a = a
    }
    def this(b: Int) {
      this(constA, b)
      this.b = b
    }
    def this() {
      this(constA, constB)
    }
  }

  class Car (val manufacturer: String, var brand: String, var model: String) {
    var speed: Double = 0;
    var gear: Any = 0;
    var isOn: Boolean = false;

    def start(keyType: String): String = {
      s"Car started using the $keyType"
    }

    def selectGear(gearNumber: Any): String = {
      gear = gearNumber
      s"Gear has been changed to $gearNumber"
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
    override def start(keyType: String): String = {
      if (isOn) {
        return "Car is already on."
      }
      if (transmission == "automatic") {
        isOn = true
        s"Car started using the $keyType"
      } else {
        isOn = true
        s"Please ensure you're holding down the clutch. Car started using the $keyType"
      }
    }
  }

  object Prediction {
    implicit class AgeFromName(name: String) {
      val r = new scala.util.Random
      def predictAge(): Int = 10 + r. nextInt(90)
    }
  }

  class PlayList {
    var songs: List[Song] = Nil

    def addSong(song: Song): Unit = {
      songs = song :: songs
    }

    class Song(title: String, artist: String)
  }
}
