package com.baeldung.scala.traits

class Score(var composer: String,
            var engineer: String,
            var orchestra: String,
            var mixer: String,
            override val qualityRatio: Double,
            var studio: String)
  extends RecordLabel with Composition with SoundProduction {

  override def compose(): String =
    s"""The score is composed by $composer,
       |Orchestration by $orchestra,
       |Mixed by $mixer""".stripMargin

  override def produce(): String = s"The score is produced by $engineer"

  override def algorithm(): MixingAlgorithm = {
    if (qualityRatio < 3) LowInstrumentalQuality
    else super.algorithm
  }

  override def getStudio(): String =
    super[Composition].getStudio() + ", " + super[SoundProduction].getStudio()
}
