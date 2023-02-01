package com.baeldung.scala3.intersectiontypes

object BasicIntersectionType {

  trait Scissors:
    def cut: Unit

  trait Needle:
    def sew: Unit

  def fixDressOne(dressFixer: Scissors & Needle) =
    dressFixer.cut
    dressFixer.sew

  def fixDressTwo(dressFixer: Needle & Scissors) =
    dressFixer.cut
    dressFixer.sew

  object DressFixer extends Scissors, Needle {
    override def cut = print("Cutting dress ")

    override def sew = print("Sewing dress ")
  }

  trait Knife:
    def cut: Unit

  trait Chainsaw:
    def cut: Unit

  object PaperCutter extends Knife, Scissors {
    override def cut = print("Cutting stuff")
  }

  def cutPaper(pc: Knife & Scissors) =
    pc.cut

  trait OneGenerator:
    def generate: Int = 1

  trait TwoGenerator:
    def generate: Int = 2

  object NumberGenerator21 extends OneGenerator, TwoGenerator {
    override def generate = super.generate
  }

  object NumberGenerator12 extends TwoGenerator, OneGenerator {
    override def generate = super.generate
  }

  def generateNumbers(generator: OneGenerator & TwoGenerator) =
    generator.generate
}
