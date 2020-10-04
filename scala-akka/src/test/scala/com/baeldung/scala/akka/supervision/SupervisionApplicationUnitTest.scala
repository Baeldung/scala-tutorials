package com.baeldung.scala.akka.supervision

import akka.actor.testkit.typed.scaladsl.{ActorTestKit, LoggingTestKit}
import akka.actor.typed.ActorSystem
import com.baeldung.scala.akka.supervision.SupervisionApplication.Main.{Created, Start}
import com.baeldung.scala.akka.supervision.SupervisionApplication.WebServer.{Get, Response}
import org.scalatest.{BeforeAndAfterAll, FlatSpec}

class SupervisionApplicationUnitTest extends FlatSpec with BeforeAndAfterAll {

  val testKit: ActorTestKit = ActorTestKit()
  implicit val actorSystem: ActorSystem[_] = testKit.internalSystem

  "The Main actor" should "log that the WebServer stopped" in {
    val mainClient = testKit.createTestProbe[Created]()
    val mainActor = testKit.spawn(SupervisionApplication.Main(), "webServer")
    LoggingTestKit.error("Child actor akka://SupervisionApplicationUnitTest/user/webServer/ws1 failed with error null")
      .expect {
        mainActor ! Start("ws1", mainClient.ref)
        val createdMsg = mainClient.receiveMessage()
        createdMsg.webServer ! Get("http://stop", testKit.createTestProbe[Response]().ref)
      }
  }

  override protected def afterAll(): Unit = testKit.shutdownTestKit()
}
