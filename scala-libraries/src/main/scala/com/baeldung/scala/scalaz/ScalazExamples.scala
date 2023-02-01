package com.baeldung.scala.scalaz

import scalaz._

object ScalazExamples {
  case class UserId(id: Long)
  case class FullName(fname: String, lname: String)
  case class User(id: UserId, name: FullName)
  val userFullName = Lens.lensu[User, FullName](
    (user, name) => user.copy(name = name),
    _.name
  )
  val firstName = Lens.lensu[FullName, String](
    (fullName, firstName) => fullName.copy(fname = firstName),
    _.fname
  )

  val lastName = Lens.lensu[FullName, String](
    (fullName, lastName) => fullName.copy(lname = lastName),
    _.lname
  )
  val userFirstName = userFullName >=> firstName
  val userLastName = userFullName >=> lastName

  implicit object threadShow extends Show[Thread] {
    override def show(f: Thread): Cord =
      Cord(s"Thread id = ${f.getId}, name = ${f.getName}")
  }

  case class Score(amt: Double)
  implicit object scoreOrdered extends Order[Score] {
    override def order(x: Score, y: Score): Ordering =
      x.amt compare y.amt match {
        case 1  => Ordering.GT
        case 0  => Ordering.EQ
        case -1 => Ordering.LT
      }
  }

  // enum
  case class Priority(num: Int, name: String)
  val HIGH = Priority(3, "HIGH")
  val MEDIUM = Priority(2, "MEDIUM")
  val LOW = Priority(1, "LOW")

  implicit object PriorityEnum extends Enum[Priority] {
    def order(p1: Priority, p2: Priority): Ordering =
      (p1.num compare p2.num) match {
        case -1 => Ordering.LT
        case 0  => Ordering.EQ
        case 1  => Ordering.GT
      }

    def succ(s: Priority): Priority = s match {
      case LOW    => MEDIUM
      case MEDIUM => HIGH
      case HIGH   => LOW
    }

    def pred(s: Priority): Priority = s match {
      case LOW    => HIGH
      case MEDIUM => LOW
      case HIGH   => MEDIUM
    }

    override def max = Some(HIGH)
    override def min = Some(LOW)
  }
}
