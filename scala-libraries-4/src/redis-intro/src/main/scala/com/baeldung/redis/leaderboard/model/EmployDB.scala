package com.baeldung.redis.leaderboard.model

object EmployDB {

  private val Dept1 = "Dept1"
  private val Dept2 = "Dept2"

  private val Prj1 = "Prj1"
  private val Prj2 = "Prj2"
  private val Prj3 = "Prj3"

  val Emp1: EmployeeKey = EmployeeKey(Dept1, Prj1, "Emp1")
  val Emp2: EmployeeKey = EmployeeKey(Dept1, Prj2, "Emp2")
  val Emp3: EmployeeKey = EmployeeKey(Dept1, Prj3, "Emp3")
  val Emp4: EmployeeKey = EmployeeKey(Dept2, Prj1, "Emp4")
  val Emp5: EmployeeKey = EmployeeKey(Dept2, Prj2, "Emp5")
  val Emp6: EmployeeKey = EmployeeKey(Dept2, Prj3, "Emp6")

}
