package com.baeldung.scala.flattening

object Flattener {

  /** This wrapper "pimps" the Seq type, adding the `fullFlat` method.
    *
    * @param seq
    *   sequence whose functionality will be expanded
    * @return
    *   a wrapper object that implements the `fullFlat` method
    */
  implicit def sequenceFlattener(seq: Seq[Any]): FullFlat =
    new FullFlat(seq)

  class FullFlat(seq: Seq[Any]) {
    def fullFlatten: Seq[Any] = seq flatten {
      case seq: Seq[Any] => seq.fullFlatten
      case nonSeq        => Seq(nonSeq)
    }
  }
}
