package com.baeldung.scala.cakepattern

import scala.collection.JavaConverters._

object CakePattern {

  trait TestEnvironmentComponent {
    val env: TestEnvironment
    trait TestEnvironment {
      val envName: String
      def readEnvironmentProperties: Map[String, String]
    }
    class WindowsTestEnvironment extends TestEnvironment {
      override val envName: String = "Windows"
      override def readEnvironmentProperties: Map[String, String] =
        System.getenv().asScala.toMap
    }
  }

  trait TestExecutorComponent { this: TestEnvironmentComponent =>
    val testExecutor: TestExecutor
    class TestExecutor {
      def execute(tests: List[Test]): Boolean = {
        println(s"Executing test with ${env.envName} environment")
        tests.forall(_.execute(env.readEnvironmentProperties))
      }
    }
  }

  trait LoggingComponent {
    val logger: Logger
    class Logger {
      def log(level: String, message: String): Unit =
        println(s"$level   - $message")
    }
  }

  trait TestExecutorComponentWithLogging {
    this: TestEnvironmentComponent with LoggingComponent =>
    val testExecutor: TestExecutor
    class TestExecutor {
      def execute(tests: List[Test]): Boolean = {
        logger.log("INFO", s"Executing test with ${env.envName} environment")
        tests.forall(_.execute(env.readEnvironmentProperties))
      }
    }
  }

  case class Test(name: String, assertion: Map[String, String] => Boolean) {
    def execute(env: Map[String, String]): Boolean = {
      println(s"Execute test $name with environment $env")
      assertion.apply(env)
    }
  }

  object Registry extends TestExecutorComponent with TestEnvironmentComponent {
    override val env: Registry.TestEnvironment = new WindowsTestEnvironment
    override val testExecutor: Registry.TestExecutor = new TestExecutor
  }
}
