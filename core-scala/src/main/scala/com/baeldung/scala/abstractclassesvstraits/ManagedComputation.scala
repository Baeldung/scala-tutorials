package com.baeldung.scala.abstractclassesvstraits

abstract class ManagedComputation[A](val identifier: String) extends Computation[A]
