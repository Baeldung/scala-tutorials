package com.baeldung.scala3.multiversalequality
import scala.language.strictEquality

object CanEqualGivenInstance extends App {

  trait Mail() {
    val fromName: String
    val toName: String
    val subject: String
    val content: String

    override def equals(that: Any): Boolean =
      that match
        case mail: Mail =>
          if this.fromName == mail.fromName &&
            this.toName == mail.toName &&
            this.subject == mail.subject &&
            this.content == mail.content
          then true
          else false
        case _ =>
          false
  }

  case class Email(
    fromName: String,
    toName: String,
    subject: String,
    content: String,
    toEmailId: String
  ) extends Mail
  case class Letter(
    fromName: String,
    toName: String,
    subject: String,
    content: String,
    toAddress: String
  ) extends Mail

  given CanEqual[Email, Letter] = CanEqual.derived

  val email = Email("John", "Annie", "Hii", "How are you", "annie@xyz.com")
  val letter =
    Letter("John", "Annie", "Hii", "How are you", "16th Street, ParkLane, LA")

  println(email == letter) // Compiles and prints true
}
