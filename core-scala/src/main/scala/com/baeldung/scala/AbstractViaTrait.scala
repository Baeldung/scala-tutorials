package com.baeldung.scala

import org.joda.time.{DateTime, Days, Years}

object AbstractViaTrait {

  // define abstract class with constructor parameters
  abstract class Employee(val name: String, val dob: DateTime) {
    def age : Int =
      Years.yearsBetween( dob, DateTime.now() ).getYears

  }

  // abstract class use: can be class or case class
  case class Engineer (firstName: String, personDob: DateTime, department: String)
    extends Employee(name = firstName, dob = personDob)

  case class Manager (firstName: String, personDob: DateTime, workForDep: String) extends Employee(name = firstName, personDob)  {
    val department: String = workForDep
  }

  // define trait with concrete method
  trait AgeHelper {
    def days(dob: DateTime) : Int = Days.daysBetween( dob, DateTime.now() ).getDays()
  }

  // define trait with method definitions
  trait EmployeeSearch {
    def findByName(name: String): Option[Employee]
    def findAll(name: Option[String], dept: Option[String]): Iterator[Employee]
  }

  trait DepartmentManagement {
    def assign(e: Employee, m: Manager)
    def setStatus()
  }

  // trait usage
  class CompanyManagement(people: Seq[Employee]) extends EmployeeSearch  {

    override def findByName(name: String): Option[Employee] = people.find(p => p.name.contains(name))

    override def findAll(name: Option[String], dept: Option[String]): Iterator[Employee] = ???
  }

  // Usage example
  def main(arg: Array[String]) : Unit = {

    val headOfEngineering = Manager("Jane", DateTime.parse("1985-5-9"), "Engineering")

    val managerWithAgeHelper = new Manager("Jane", DateTime.parse("1985-5-9"), "Engineering") with AgeHelper

    println( managerWithAgeHelper.days(managerWithAgeHelper.personDob) )

    val companyStaff = Seq(
      Engineer("Alex", DateTime.parse("1981-1-1"), "Engineering"),
      Engineer("John", DateTime.parse("1985-4-3"), "Engineering" ),
      Engineer("George", DateTime.parse("1971-4-8"), "Engineering"),
      headOfEngineering
     )

    val companyManagement = new CompanyManagement(companyStaff)
    val person = companyManagement.findByName("Alex")

    println( person.map(_.age) )
  }

}
