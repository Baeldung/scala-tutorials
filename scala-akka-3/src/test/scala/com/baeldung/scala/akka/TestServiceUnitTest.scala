package com.baeldung.scala.akka

import org.scalatest.BeforeAndAfterAll
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import akka.actor.testkit.typed.scaladsl.ActorTestKit

class TestServiceUnitTest extends AnyWordSpec with BeforeAndAfterAll with Matchers {
  val testKit = ActorTestKit()
  implicit val system = testKit.system

  // responsible for shutting down the ActorSystem
  override def afterAll(): Unit = testKit.shutdownTestKit()
}
