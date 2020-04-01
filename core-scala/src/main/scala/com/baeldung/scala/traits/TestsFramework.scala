package com.baeldung.scala.traits

import org.slf4j.{Logger, LoggerFactory}

// My class implements two traits, Testable and Loggable
// It must give an implementation of each abstract method
// declared in each trait
class MyClass extends Testable with Loggable {
  override val name: String = "MyClassTest"
  override val tests: List[Test[_]] = List(
    new Test[Unit]("method1Test") {
      override def run(): Unit = info(s"Run test $name")
    }
  )

  override def setUp(): Unit = {
    info("Set up the test environment")
  }

  def method1(): Unit = {
    info("Doing some suff")
  }
}

class EmptyLoggableTest(val id: String) extends Test[Unit](id) with Loggable {
  override def run(): Unit = info("Do nothing")
}

// Represents something that can be tested
trait Testable {
  // abstract fields
  val name: String
  val tests: List[Test[_]]
  // concrete method
  def testsNames: List[String] = tests.map(_.name)
  // abstract methods
  def setUp(): Unit
}

abstract class TestSuite(private val tests: List[Testable]) {
  // The runAll is abstract. Every concrete type of TestSuite must
  // implement it
  def runAll(): Unit
}

abstract class Test[Type](val name: String) {
  def run(): Type
}

trait LoggableResult[Type] extends Result[Type] with Loggable {
  abstract override val result: Type = {
    val res = super.result
    info("The result is $res")
    res
  }
}

trait Result[Type] {
  def result: Type
}

trait Loggable {
  val logger: Logger = LoggerFactory.getLogger(getClass)
  def info(message: String): Unit = logger.info(message)
}

// Scala code
trait CallableFromJava {
  def callableMethod(): Unit = println("You can call me from Java!")
  def methodToImplement(): Unit
}