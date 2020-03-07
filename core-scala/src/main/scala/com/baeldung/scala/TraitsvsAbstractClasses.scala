package com.baeldung.scala

/**
 * Practical difference between traits and abstract classes
 *
 * @author Chaitanya Waikar
 *
 */

abstract class A

abstract class B

class C extends A

//class D extends A with B
// error: class B needs to be a trait to be mixed in

trait Base {
  def name: String
}

trait Foo extends Base {
  override def name = "foo"
}

trait Bar extends Base {
  override def name = "bar"
}

class Extender1 extends Foo with Bar

class Extender2 extends Bar with Foo


trait Logger {
  def log(message: String): String = {
    message
  }
}
