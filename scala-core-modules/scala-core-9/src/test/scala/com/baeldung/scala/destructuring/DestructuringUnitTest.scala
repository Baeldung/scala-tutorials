package com.baeldung.scala.destructuring

import scala.util.Random

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class DestructuringUnitTest extends AnyFlatSpec with Matchers {
  val random = new Random

  def getRandomElement[A](seq: Seq[A], random: Random): A = seq(
    random.nextInt(seq.length)
  )

  // Sample notifications
  val emailNotification1 =
    Email("Greeting", "Hello, Scala user!", "user@example.com")
  val emailNotification2 =
    Email("Follow up", "Hello, are you loving scala?", "user@example.com")
  val emails = List(emailNotification1, emailNotification2)
  val smsNotification = SMS("1234567890", "Your verification code is 12345")
  val notifications = List(emailNotification1, smsNotification)

  "We" should "be able to pattern match without @" in {
    getRandomElement(notifications, random) match {
      case Email(subject, body, recipient) =>
        println(s"Logging Email to $recipient with subject $subject")
        // Reassemble and call the specific handler
        processEmail(
          Email(subject, body, recipient)
        ).msg startsWith ("Sent email to ")

      case SMS(number, message) =>
        println(s"Logging SMS to $number: $message")
        // Reassemble and call the specific handler
        processSMS(SMS(number, message)).msg startsWith ("Sending SMS to")
    }
  }

  "We" should "be able to pattern match on the types without @" in {
    getRandomElement(notifications, random) match {
      case email: Email =>
        println(
          s"Logging Email to ${email.recipient} with subject ${email.subject}"
        )
        // Reassemble and call the specific handler
        processEmail(
          email
        ).msg startsWith ("Sent email to ")

      case sms: SMS =>
        println(s"Logging SMS to ${sms.number}: ${sms.message}")
        // Reassemble and call the specific handler
        processSMS(sms).msg startsWith ("Sending SMS to")
    }
  }

  "We" should "be able to pattern match with @" in {
    getRandomElement(notifications, random) match {
      case email @ Email(subject, _, recipient) =>
        println(s"Logging Email to $recipient with subject $subject")
        processEmail(email).msg startsWith ("Sent email to ")

      case sms @ SMS(number, message) =>
        println(s"Logging SMS to $number: $message")
        // Reassemble and call the specific handler
        processSMS(sms).msg startsWith ("Sending SMS to")
    }
  }

  "We" should "be able to use @ while declaring a variable" in {
    val email @ Email(subject, _, recipient) = getRandomElement(emails, random)
    println(s"Logging Email to $recipient with subject $subject")
    processEmail(email).msg startsWith ("Sent email to ")
  }

  "We" should "be able to use @ in for comprehensions" in {
    for (email @ Email(subject, _, recipient) <- emails) {
      println(s"Logging Email to $recipient with subject $subject")
      processEmail(email).msg startsWith ("Sent email to ")
    }
  }

  "We" should "be able to use @ in a subpattern" in {
    emails match {
      case _ :: (email2 @ Email(subject, _, recipient)) :: _ =>
        println(s"Logging 2nd Email to $recipient with subject $subject")
        processEmail(email2).msg startsWith ("Sent email to ")

      case _ =>
        println(s"Only one message")
    }
  }

}
