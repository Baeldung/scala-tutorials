package com.baeldung.scala.abstractclassesvstraits

object myClasses {
  //defining a trait FruitTrait
  trait FruitTrait {
    val name: String
    val color: String
    def taste(): Unit
  }

//defining an abstract class FruitAbstractClass
  abstract class FruitAbstractClass {
    val name: String
    val color: String
    def taste(): Unit
  }

//defining a trait NumTrait
  trait NumTrait {
    val number: Int
  }

//defining an abstract class NumAbstractClass
  abstract class NumAbstractClass {
    val number: Int
  }

  class MyFruit1 extends FruitTrait with NumTrait {
    override val name: String = "Apple"
    override val color: String = "Green"
    override val number: Int = 3
    override def taste(): Unit =
      println(s"my $number $color " + name + "s taste yummy")
  }

//class MyFruit2 extends FruitAbstractClass with NumAbstractClass {
  //override val name: String = "Apple"
  //override val color: String = "Red"
  //override val number: Int = 2
  //override def taste(): Unit = println(s"my $number $color " + name + "s taste yummy")
//} NumAbstractClass needs to be a trait to be mixed in

//defining an abstract class with constructor arguments
  abstract class FruitAbstractClass2(n: String, c: String) {
    val name: String = n
    val color: String = c
    def taste(): Unit
  }

  class MyFruit2 extends FruitAbstractClass2("Apple", "Red") {
    override def taste(): Unit = println(s"my $color $name tastes sweet")
  }

//trait FruitTrait(n: String, c: String) {
  //      val name: String = n
  //      val color: String = c
  //      def taste(): Unit = println(s"I love my $color $name")
//} //traits or objects may not have parameters

  class Age
  trait HeightTrait
  abstract class HeightAC
}

object AbstractclassesVsTraits extends App {
  import myClasses._

  val fruit2 = new MyFruit2
  fruit2.taste // my Red Apple tastes sweet
  val fruit = new MyFruit1
  fruit.taste // my 3 Green Apples taste yummy

  val myAgeT = new Age with HeightTrait // compiles
  //val myAgeAC = new Age with HeightAC // throws error
}