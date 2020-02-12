package com.baeldung.scala

abstract class EventSender {
  def envelope(payload: String): String
}

class SimpleEventSender extends EventSender {
  def envelope(payload: String): String = {
    s"""{ payload: $payload }"""
  }
}

trait EventFirstSender extends EventSender {
  abstract override def envelope(payload: String): String = {
    s"""{ firstSender: ${super.envelope(payload)} }"""
  }
}

trait EventSecondSender extends EventSender {
  abstract override def envelope(payload: String): String = {
    s"""{ secondSender: ${super.envelope(payload)} }"""
  }
}

trait EventThirdSender extends EventSender {
  abstract override def envelope(payload: String): String = {
    s"""{ thirdSender: ${super.envelope(payload)} }"""
  }
}





