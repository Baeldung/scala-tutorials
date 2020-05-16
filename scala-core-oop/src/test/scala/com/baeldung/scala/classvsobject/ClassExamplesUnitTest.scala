package com.baeldung.scala.classvsobject

import com.baeldung.scala.classvsobject.ClassExamples._
import org.junit.Assert._
import org.junit.Test

class ClassExamplesUnitTest {

  @Test
  def givenClassCar_whenInstantiated_thenTheInstanceHasAllAttributesOfCar(): Unit = {
    var car1 = new Car("Toyota", "SUV", "RAV4")

    assertEquals(car1.start("remote"), "Car started using the remote")
    assertEquals(car1.speed, 0.0, 0)
    assertEquals(car1.accelerate(2, 5), "Car accelerates at 2.0 per second for 5.0 seconds.")
    assertEquals(car1.selectGear("D"), "Gear has been changed to D")
    assertEquals(car1.brake(1, 3), "Car slows down at 1.0 per second for 3.0 seconds.")
    assertEquals(car1.speed, 7.0, 0)
    assertEquals(car1.stop(), "Car has stopped.")
    assertEquals(car1.speed, 0.0, 0)
    assertEquals(car1.speed, 0.0, 0)
  }

  @Test
  def givenToyotaExtendsCar_whenInstantiated_thenToyotaHasAllAttributesOfCarWithModifiedStartMethod(): Unit = {
    var prado = new Toyota("Manual", "SUV", "Prado")

    assertEquals(prado.start("remote"), "Please ensure you're holding down the clutch. Car started using the remote")
    assertEquals(prado.speed, 0.0, 0)
    assertEquals(prado.accelerate(2, 5), "Car accelerates at 2.0 per second for 5.0 seconds.")
    assertEquals(prado.selectGear("D"), "Gear has been changed to D")
    assertEquals(prado.brake(1, 3), "Car slows down at 1.0 per second for 3.0 seconds.")
    assertEquals(prado.speed, 7.0, 0)
    assertEquals(prado.stop(), "Car has stopped.")
    assertEquals(prado.speed, 0.0, 0)
    assertEquals(prado.speed, 0.0, 0)
  }

  @Test
  def givenPrediction_whenPredictAgeIsCalledOnString_thenAnIntegerIsReturned(): Unit = {
    import ClassExamples.Prediction._

    assertTrue("Edith".predictAge() < 101 & "Edith".predictAge() > 9)
  }

  @Test
  def givenPlayList_whenSongsAreCreatedFromOnePlayListInstance_thenSongsCanBeAddedUsingAddSongMethod(): Unit = {
    val funk = new PlayList
    val jazz = new PlayList
    val song1 = new funk.Song("We celebrate", Artist("Laboriel", 56, "Nigeria", "Bassist"))
    val song2 = new funk.Song("We celebrate 2", Artist("Laboriel A", 54, "Nigeria", "Bassist"))
    val song3 = new jazz.Song("Amazing grace", Artist("Victor Wooten", 47, "USA", "Lead vocalist"))
    val song4 = new jazz.Song("Amazing grace", Artist("Victor Wooten A", 34, "USA", "Lead vocalist"))
    funk.addSong(song1)
    funk.addSong(song2)
    jazz.addSong(song3)
    jazz.addSong(song4)

    assertEquals(funk.songs, List(song2, song1))
    assertEquals(jazz.songs, List(song4, song3))
  }
}