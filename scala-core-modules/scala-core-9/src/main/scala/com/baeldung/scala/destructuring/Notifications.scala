package com.baeldung.scala.destructuring

trait Notification
case class Email(subject: String, body: String, recipient: String)
  extends Notification
case class SMS(number: String, message: String) extends Notification

case class Processed[N <: Notification](notification: N, msg: String)

def processEmail(email: Email): Processed[Email] = Processed(
  email,
  s"Sent email to ${email.recipient} with subject: ${email.subject}"
)

def processSMS(sms: SMS): Processed[SMS] =
  Processed(sms, s"Sending SMS to ${sms.number}: ${sms.message}")
