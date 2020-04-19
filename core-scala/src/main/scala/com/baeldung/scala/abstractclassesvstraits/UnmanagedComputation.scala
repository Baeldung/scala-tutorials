package com.baeldung.scala.abstractclassesvstraits

trait UnmanagedComputation[A] extends Computation[A] {

  val identifier: String = java.util.UUID.randomUUID().toString

}
