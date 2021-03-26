package com.baeldung.scala.abstractclassestrait

trait IntQueue {
  var seq: Seq[Int] = Seq.empty

  def get(): Option[Int] = {
    val last = seq.lastOption
    seq = seq.drop(1)
    last
  }

  def put(elem: Int): Unit = {
    seq = seq :+ elem
  }
}

trait Doubling extends IntQueue {
  override def put(elem: Int): Unit = super.put(2 * elem)
}

trait Filtering extends IntQueue {

  val mustBeGreaterThan: Int

  override def put(elem: Int): Unit =
    if (elem > mustBeGreaterThan) super.put(elem)
}
