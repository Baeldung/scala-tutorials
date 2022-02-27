package com.baeldung.scala.scalatest


import org.scalactic.StringNormalizations._
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class ExampleFlatSpecWithMatchersUnitTest extends AnyFlatSpec with Matchers {

  "With a matcher" should "let us check equality" in {
    val number = 25
    number should equal(25)
    number shouldEqual 25
  }

  it should "also let us customize equality" in {
    " baeldung  " should equal("baeldung")(after being trimmed)
  }

  it should "let us check equality using be" in {
    val number = 25
    number should be(25)
    number shouldBe 25
  }

  it should "let us check the length of strings" in {
    val text = "baeldung"
    text should have length 8
  }

  it should "let us check the size of collections" in {
    val days = List("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")
    days should have size 7
  }

  it should "let us check different parts of a string" in {
    val headline = "Baeldung is really cool!"
    headline should startWith("Baeldung")
    headline should endWith("cool!")
    headline should include("really")
  }

  it should "let us check that an email is valid" in {
    "baeldung@somewhere.com" should fullyMatch regex """[^@]+@[^\.]+\..+"""
  }

  ignore should "let us check a list is empty" in {
    List.empty shouldBe empty
  }

  it should "let us check a list is NOT empty" in {
    List(1, 2, 3) should not be empty
  }

  it should "let us check a map contains a given key and value" in {
    Map('x' -> 10, 'y' -> 20, 'z' -> 30) should contain('y' -> 20)
  }

  it should "let us check the type of an object" in {
    List(1, 2, 3) shouldBe a[List[_]]
    List(1, 2, 3) should not be a[Map[_, _]]
  }

}
