package com.baeldung.scala3.intersectiontypes

object Inheritance {

  trait Scissors:
    def cut: Unit

  trait Needle:
    def sew: Unit

  trait Tools extends Scissors, Needle

  object DressFixer extends Tools {
    override def cut = print("Cutting dress ")
    override def sew = print("Sewing dress ")
  }

  def fixDress(dressFixer: Tools) =
    dressFixer.cut
    dressFixer.sew

  trait Knife extends CuttingTool:
    def cut: Unit

  trait Chainsaw extends CuttingTool:
    def cut: Unit

  trait CuttingTool:
    def cut: Unit

  def cutWithInheritance(cuttingTool: CuttingTool) =
        cuttingTool.cut
}
