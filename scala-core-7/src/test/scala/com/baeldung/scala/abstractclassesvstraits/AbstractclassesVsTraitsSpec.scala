package com.baeldung.scala.abstractclassesvstraits

import org.scalatest.{FlatSpec, Matchers}
import myClasses._

class AbstractclassesVsTraitsSpec extends FlatSpec with Matchers {
  "FruitTrait" should "contain 2 string values and a unit value" in {
    class Foo extends FruitTrait {
      override val name = "Banana"
      override val color = "yellow"
      override def taste = println("Yellow banana")
    }

    val fooTest = new Foo
    fooTest.name shouldBe an[String]
    fooTest.color shouldBe an[String]
    fooTest.taste shouldBe an[Unit]
  }

  "FruitAbstractClass" should "contain 2 string values and a unit value" in {
    class Foo extends FruitAbstractClass {
      override val name = "Orange"
      override val color = "green"
      override def taste = println("green orange")
    }

    val fooTest = new Foo
    fooTest.name shouldBe an[String]
    fooTest.color shouldBe an[String]
    fooTest.taste shouldBe an[Unit]
  }

  "NumTrait" should "contain an Int" in {
    class Bar extends NumTrait {
      override val number = 4
    }
    val barTest = new Bar
    barTest.number shouldBe an[Int]
  }

  "NumAbstractClass" should "contain an Int" in {
    class Bar extends NumAbstractClass {
      override val number = 5
    }
    val barTest = new Bar
    barTest.number shouldBe an[Int]
  }

  "MyFruit1" should "contain the following" in {
    val myFruit1 = new MyFruit1
    myFruit1.name should be("Apple")
    myFruit1.color should be("Green")
    myFruit1.number should be(3)
    myFruit1.taste shouldBe an[Unit]
  }

  "FruitAbstractClass2" should "contain 2 string values and a unit value" in {
    class Foo extends FruitAbstractClass2("Apple", "Red") {
      override def taste(): Unit = println("Green Apple")
    }

    val fooTest = new Foo
    fooTest.name shouldBe an[String]
    fooTest.color shouldBe an[String]
    fooTest.taste shouldBe an[Unit]
  }
}
