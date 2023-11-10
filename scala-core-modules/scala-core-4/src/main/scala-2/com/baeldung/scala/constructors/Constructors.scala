package com.baeldung.scala.constructors

object Constructors {

  /** Regular constructors
    */
  //  class Employee(name : String, email : String)
  //  val employee = new Employee("John Doe","jd@yahoo.com")

  //  class Employee(name: String, email: String, role: Int)
  //  val worker = new Employee("John Doe","jd@yahoo.com",0)
  //  val manager = new Employee("John Smith","js@yahoo.com",1)

  /** Private Constructors
    */
  //  class Employee private(name: String, email: String, role: Int)
  //  object Employee {
  //    def createWorker(name: String, email: String): Employee = new Employee(name, email, 0)
  //
  //    def createManager(name: String, email: String): Employee = new Employee(name, email, 1)
  //  }
  //  val worker = Employee.createWorker("John Doe", "jd@yahoo.com")
  //  val manager = Employee.createManager("John Smith", "js@yahoo.com")

  /** Protected Constructors
    */
  class Employee protected (name: String, email: String, role: Int)
  class Worker(name: String, email: String) extends Employee(name, email, 0)
  class Manager(name: String, email: String) extends Employee(name, email, 1)

  val worker = new Worker("John Doe", "jd@yahoo.com")
  val manager = new Manager("John Smith", "js@yahoo.com")

}
