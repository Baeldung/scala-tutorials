package com.baeldung.scala.selftype

import com.baeldung.scala.selftype.SelfType.Test

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

  object Registry extends TestExecutorComponent with TestEnvironmentComponent {
    override val env: Registry.TestEnvironment = new WindowsTestEnvironment
    override val testExecutor: Registry.TestExecutor = new TestExecutor
  }
}
