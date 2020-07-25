package com.baeldung.scala.adt

object Examples {
  sealed trait Color
  final case object White extends Color
  final case object Black extends Color

  sealed trait Name
  final case object Pawn extends Name
  final case object Rook extends Name
  final case object Knight extends Name
  final case object Bishop extends Name
  final case object Queen extends Name
  final case object King extends Name

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

  trait Felin
  trait Animal
  trait Cat extends Animal with Felin
}
