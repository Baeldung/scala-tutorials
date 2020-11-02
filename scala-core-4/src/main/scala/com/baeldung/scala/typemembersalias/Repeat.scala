package com.baeldung.scala.typemembersalias

trait Repeat {
  type RepeatType
  def apply(item: RepeatType, reps: Int): RepeatType
}

object IntegerRepeat extends Repeat {
  type RepeatType = Int
  def apply(item: RepeatType, reps: Int): RepeatType = {
    (item.toString * reps).toInt
  }
}

object StringRepeat extends Repeat {
  type RepeatType = String
  def apply(item: RepeatType, reps: Int): RepeatType = {
    item * reps
  }
}

object ListRepeat extends Repeat {
  type RepeatType = List[Any]
  def apply(item: RepeatType, reps: Int): RepeatType = {
    (1 to reps).map(_ => item).reduce(_ ::: _)
  }
}