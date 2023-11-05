package com.baeldung.scala.foldvsreduce

object FoldLeftVsReduceLeft {

  case class Person(name: String, age: Int)

  def getAllPeople(people: Map[Int, Person]): List[Person] = {
    people.foldLeft(List.empty[Person])((people, current) => {
      people :+ current._2
    })
  }

  def getYoungestPerson(people: List[Person]): Person = {
    people.reduceLeft((youngestPerson, currentPerson) => {
      if (youngestPerson.age > currentPerson.age) {
        currentPerson
      } else {
        youngestPerson
      }
    })
  }
}
