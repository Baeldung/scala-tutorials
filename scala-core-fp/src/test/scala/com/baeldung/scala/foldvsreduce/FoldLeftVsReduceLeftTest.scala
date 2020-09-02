package com.baeldung.scala.foldvsreduce

import org.scalatest.{Matchers, _}
import com.baeldung.scala.foldvsreduce.FoldLeftVsReduceLeft.Person

class FoldLeftVsReduceLeftTest extends FlatSpec with Matchers {

  it should "get a list of people from  map of id and person" in {
    val people: Map[Int, Person] = Map(1 -> Person("Tom", 10), 2 -> Person("Gillian", 13), 3 -> Person("Sarah", 17), 4 -> Person("David", 20))
    val peopleList = FoldLeftVsReduceLeft.getAllPeople(people)
    peopleList shouldBe List(Person("Tom", 10), Person("Gillian", 13), Person("Sarah", 17), Person("David", 20))
  }

  it should "reduce list of people to youngest person" in {
    val peopleList = List(Person("Tom", 10), Person("Gillian", 13), Person("Sarah", 17), Person("David", 20))
    val youngestUser = FoldLeftVsReduceLeft.getYoungestPerson(peopleList)
    youngestUser shouldBe Person("Tom", 10)
  }
}