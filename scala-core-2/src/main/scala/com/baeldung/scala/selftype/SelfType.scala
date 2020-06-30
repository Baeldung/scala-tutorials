package com.baeldung.scala.selftype

object SelfType {

  trait TestEnvironment {
    val envName: String
    def readEnvironmentProperties: Map[String, String]
  }

  trait TestExecutor { env: TestEnvironment =>
    def execute(tests: List[Test]): Boolean = {
      println(s"Executing test with $envName environment")
      tests.forall(_.execute(readEnvironmentProperties))
    }
  }

  case class Test(name: String, assertion: Map[String, String] => Boolean) {
    def execute(env: Map[String, String]): Boolean = {
      println(s"Execute test $name with environment $env")
      assertion.apply(env)
    }
  }
}
