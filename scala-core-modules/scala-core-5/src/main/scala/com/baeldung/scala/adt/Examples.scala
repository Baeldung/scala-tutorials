package com.baeldung.scala.adt

object Examples {
  sealed trait Color
  case object White extends Color
  case object Black extends Color

  sealed trait Name
  case object Pawn extends Name
  case object Rook extends Name
  case object Knight extends Name
  case object Bishop extends Name
  case object Queen extends Name
  case object King extends Name

  case class ChessPiece(color: Color, name: Name)

  def isTheMostImportantPiece(c: ChessPiece): Boolean = c match {
    case ChessPiece(_, King) => true
    case _                   => false
  }

  trait Semaphore {
    val color: SemaphoreColor
  }

  sealed trait SemaphoreColor
  final case object Green extends SemaphoreColor
  final case object Amber extends SemaphoreColor
  final case object Red extends SemaphoreColor

  trait Feline
  trait Animal
  trait Cat extends Animal with Feline
}
