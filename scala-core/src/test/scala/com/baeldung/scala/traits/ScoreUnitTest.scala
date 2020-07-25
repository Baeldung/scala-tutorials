package com.baeldung.scala.traits

import org.junit.Assert.assertEquals
import org.junit.Test

class ScoreUnitTest {

  @Test
  def givenScore_whenComposeCalled_thenCompositionIsReturned() = {

    val composer = "Hans Zimmer"
    val engineer = "Matt Dunkley"
    val orchestra = "Berlin Philharmonic"
    val mixer = "Dave Stewart"
    val studio = "Abbey Studios"
    val score = new Score(composer, engineer, orchestra, mixer, 10, studio)

    assertEquals(score.compose(),
      s"""The score is composed by $composer,
         |Orchestration by $orchestra,
         |Mixed by $mixer""".stripMargin)
  }

  @Test
  def givenScore_whenProduceCalled_thenSoundProductionIsReturned() = {

    val composer = "Hans Zimmer"
    val engineer = "Matt Dunkley"
    val orchestra = "Berlin Philharmonic"
    val mixer = "Dave Stewart"
    val studio = "Abbey Studios"
    val score = new Score(composer, engineer, orchestra, mixer, 3, studio)

    assertEquals(score.produce(), s"The score is produced by $engineer")
  }

  @Test
  def givenScore_whenLowQualityRatioSet_thenCorrectAlgorithmIsReturned() = {

    val composer = "Hans Zimmer"
    val engineer = "Matt Dunkley"
    val orchestra = "Berlin Philharmonic"
    val mixer = "Dave Stewart"
    val studio = "Abbey Studios"
    val score = new Score(composer, engineer, orchestra, mixer, 1, studio)

    assertEquals(score.algorithm().toString, "Low instrumental quality")
  }

  @Test
  def givenScore_whenHighQualityRatioSet_thenCorrectAlgorithmIsReturned() = {

    val composer = "Hans Zimmer"
    val engineer = "Matt Dunkley"
    val orchestra = "Berlin Philharmonic"
    val mixer = "Dave Stewart"
    val studio = "Abbey Studios"
    val score = new Score(composer, engineer, orchestra, mixer, 10, studio)

    assertEquals(score.algorithm().toString, "High instrumental quality")
  }

  @Test
  def givenScore_whenVocalsMixinAttached_thenSingCanBeCalled() = {

    val composer = "Hans Zimmer"
    val engineer = "Matt Dunkley"
    val orchestra = "Berlin Philharmonic"
    val mixer = "Dave Stewart"
    val studio = "Abbey Studios"
    val score = new Score(composer, engineer, orchestra, mixer, 10, studio) with Vocals

    assertEquals(score.sing, "Vocals mixin")
  }

  @Test
  def givenScore_whenGetStudioCalled_thenStudiosAreReturned() = {

    val composer = "Hans Zimmer"
    val engineer = "Matt Dunkley"
    val orchestra = "Berlin Philharmonic"
    val mixer = "Dave Stewart"
    val studio = "Abbey Studios"
    val score = new Score(composer, engineer, orchestra, mixer, 10, studio)

    assertEquals(
      score.getStudio(),
      s"Composed at studio $studio, Produced at studio $studio"
    )
  }
}
