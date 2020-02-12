package com.baeldung.scala

abstract class EventAbstract {
  val eventType: String
  var applicationId: Int = 99
  def debug = { println(toJson) }
  def toJson: String
}

trait EventTrait {
  val eventType: String
  var applicationId: Int = 99
  def debug = { println(toJson) }
  def toJson: String
}

class NewPostArrivedEventA extends EventAbstract {
  val eventType: String = "NewPost"
  def toJson: String = s"""{"type": "$eventType", "appId": $applicationId}"""
}

class NewPostArrivedEventT extends EventTrait {
  val eventType: String = "NewPost"
  def toJson: String = s"""{"type": "$eventType", "appId": $applicationId}"""
}


trait Event2 {
    val eventType: String
    var applicationId: Int = 99
}

trait Debug {
    def debug = { println(toJson) }
    def toJson: String
}

class SimpleEvent extends Event2 with Debug {
    val eventType: String = "simpleType"
    def toJson: String = s"""{"type": "$eventType", "appId": $applicationId}"""
}