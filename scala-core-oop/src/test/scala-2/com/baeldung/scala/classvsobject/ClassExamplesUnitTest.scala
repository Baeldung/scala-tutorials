package com.baeldung.scala.classvsobject

import com.baeldung.scala.classvsobject.ClassExamples._
import org.junit.Assert._
import org.junit.Test

class ClassExamplesUnitTest {

  @Test
  def givenClassAbc_whenInstantiatedWithNoArgument_thenDefaultParametersAreUsed()
    : Unit = {
    var abc = new Abc()

    assertEquals(abc.a, "A")
    assertEquals(abc.b, 4)
  }

  @Test
  def givenClassAbc_whenInstantiatedWithOnlyA_thenDefaultParametersAreUsedForB()
    : Unit = {
    var abc = new Abc(a = "New A")

    assertEquals(abc.a, "New A")
    assertEquals(abc.b, 4)
  }

  @Test
  def givenClassAbc_whenInstantiatedWithOnlyB_thenDefaultParametersAreUsedForA()
    : Unit = {
    var abc = new Abc(b = 10)

    assertEquals(abc.a, "A")
    assertEquals(abc.b, 10)
  }

  @Test
  def givenClassAbc_whenInstantiatedWithTwoArguments_thenSuppliedParametersAreUsed()
    : Unit = {
    var abc = new Abc(a = "Another A", b = 8)

    assertEquals(abc.a, "Another A")
    assertEquals(abc.b, 8)
  }

  @Test
  def givenClassCar_whenInstantiated_thenTheInstanceHasAllAttributesOfCar()
    : Unit = {
    var familyCar = new Car("Toyota", "SUV", "RAV4")

    assertEquals(familyCar.start("remote"), "Car started using the remote")
    assertEquals(familyCar.speed, 0.0, 0)
    assertEquals(
      familyCar.accelerate(2, 5),
      "Car accelerates at 2.0 per second for 5.0 seconds."
    )
    assertEquals(familyCar.selectGear("D"), "Gear has been changed to D")
    assertEquals(
      familyCar.brake(1, 3),
      "Car slows down at 1.0 per second for 3.0 seconds."
    )
    assertEquals(familyCar.speed, 7.0, 0)
    assertEquals(familyCar.stop(), "Car has stopped.")
    assertEquals(familyCar.speed, 0.0, 0)
    assertEquals(familyCar.speed, 0.0, 0)
  }

  @Test
  def givenToyotaExtendsCar_whenInstantiated_thenToyotaHasAllAttributesOfCarWithModifiedStartMethod()
    : Unit = {
    var prado = new Toyota("Manual", "SUV", "Prado")

    assertEquals(
      prado.start("remote"),
      "Please ensure you're holding down the clutch. Car started using the remote"
    )
    assertEquals(prado.speed, 0.0, 0)
    assertEquals(
      prado.accelerate(2, 5),
      "Car accelerates at 2.0 per second for 5.0 seconds."
    )
    assertEquals(prado.selectGear("D"), "Gear has been changed to D")
    assertEquals(
      prado.brake(1, 3),
      "Car slows down at 1.0 per second for 3.0 seconds."
    )
    assertEquals(prado.speed, 7.0, 0)
    assertEquals(prado.stop(), "Car has stopped.")
    assertEquals(prado.speed, 0.0, 0)
    assertEquals(prado.speed, 0.0, 0)
  }

  @Test
  def givenPrediction_whenPredictAgeIsCalledOnString_thenAnIntegerIsReturned()
    : Unit = {
    import ClassExamples.Prediction._

    assertTrue("Edith".predictAge() < 101 & "Edith".predictAge() > 9)
  }

  @Test
  def givenPlayList_whenSongsAreCreatedFromOnePlayListInstance_thenSongsCanBeAddedUsingAddSongMethod()
    : Unit = {
    val funk = new PlayList
    val jazz = new PlayList
    val song1 = new funk.Song("We celebrate", "Laboriel")
    val song2 = new funk.Song("We celebrate 2", "Laboriel A")
    val song3 = new jazz.Song("Amazing grace", "Victor Wooten")
    val song4 = new jazz.Song("Amazing grace", "Victor Wooten A")
    funk.addSong(song1)
    funk.addSong(song2)
    jazz.addSong(song3)
    jazz.addSong(song4)

    assertEquals(funk.songs, List(song2, song1))
    assertEquals(jazz.songs, List(song4, song3))
  }
}
