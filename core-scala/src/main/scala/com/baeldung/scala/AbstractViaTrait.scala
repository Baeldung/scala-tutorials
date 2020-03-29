package com.baeldung.scala

import org.joda.time.{DateTime, Days, Years}

object AbstractViaTrait {

  abstract class Employee(val name: String, val dob: DateTime) {
    def age: Int =
      Years.yearsBetween(dob, DateTime.now()).getYears
  }

  case class Engineer(firstName: String, personDob: DateTime, department: String)
    extends Employee(name = firstName, dob = personDob)

  case class Manager(firstName: String, personDob: DateTime, workForDep: String) extends Employee(name = firstName, personDob) {
    val department: String = workForDep
  }

  trait EmployeeSearch {
    def findByName(name: String): Option[Employee]

    def findAll(name: Option[String], dept: Option[String]): Iterator[Employee]
  }

  trait DepartmentManager {
    def findAll(deparment: String): Seq[Employee]
  }

  class CompanyManagement(people: Seq[Employee]) extends EmployeeSearch with DepartmentManager {
    override def findByName(name: String): Option[Employee] = people.find(p => p.name.contains(name))

    override def findAll(name: Option[String], dept: Option[String]): Iterator[Employee] = ???

    override def findAll(deparment: String): Seq[Employee] = people.filter(employee => employee match {
      case engineer: Engineer => engineer.department.equals(deparment)
      case manager: Manager => manager.workForDep.equals(deparment)
      case _ => false
    })
  }

  def main(arg: Array[String]): Unit = {

    val headOfEngineering = Manager("Jane", DateTime.parse("1985-5-9"), "Engineering")

    trait AgeHelper {
      def days(dob: DateTime): Int = Days.daysBetween(dob, DateTime.now()).getDays()
    }

    val managerWithAgeHelper = new Manager("Jane", DateTime.parse("1985-5-9"), "Engineering") with AgeHelper

    println(managerWithAgeHelper.days(managerWithAgeHelper.personDob))

    val companyStaff = Seq(
      Engineer("Alex", DateTime.parse("1981-1-1"), "Engineering"),
      Engineer("John", DateTime.parse("1985-4-3"), "Engineering"),
      Engineer("George", DateTime.parse("1971-4-8"), "Engineering"),
      headOfEngineering
    )

    val companyManagement = new CompanyManagement(companyStaff)
    val person = companyManagement.findByName("Alex")

    println(person.map(_.age))
  }

}
