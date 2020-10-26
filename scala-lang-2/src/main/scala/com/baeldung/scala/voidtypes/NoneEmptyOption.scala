package com.baeldung.scala.voidtypes

object NoneEmptyOption extends App{

  val studentRegister:Map[Int,String] = Map(1 -> "John", 2 -> "Mary")

  def getStudentName(studentRegister:Map[Int,String], roll:Int):Option[String] = {
    studentRegister.get(roll)
  }

  def printStudent(student:Option[String]): Unit ={
    student match {
      case Some(str) => println("Student Name is %s".format(str))
      case None      => println("No Student!!")
    }
  }

  val student1 = getStudentName(studentRegister, 1)
  printStudent(student1)

  val noStudent = getStudentName(studentRegister, 0)
  printStudent(noStudent)

}
