package com.baeldung.scala.AbstractClassAndTrait.Trait

trait Pet{
  def speak = println("Yo")     // concrete implementation of a speak method
  def comeToMaster(): Unit      // abstract
}
