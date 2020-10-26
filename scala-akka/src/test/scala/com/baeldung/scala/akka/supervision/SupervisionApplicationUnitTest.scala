package com.baeldung.scala.akka.supervision

import akka.actor.testkit.typed.scaladsl.{ActorTestKit, LoggingTestKit}
import akka.actor.typed.ActorSystem
import com.baeldung.scala.akka.supervision.SupervisionApplication.Cache.{Find, Hit}
import com.baeldung.scala.akka.supervision.SupervisionApplication.Main.{Created, Start}
import com.baeldung.scala.akka.supervision.SupervisionApplication.{Cache, File, Filesystem, WebServer}
import com.baeldung.scala.akka.supervision.SupervisionApplication.WebServer.{BadRequest, Get, Ok, Response}
import org.scalatest.{BeforeAndAfterAll, FlatSpec}

import scala.concurrent.duration.DurationInt

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

  "The WebServer actor" should "handle validation errors" in {
    val webServer = testKit.spawn(WebServer(), "ws1")
    val webServerClient = testKit.createTestProbe[Response]()
    webServer ! Get("Not a valid URI", webServerClient.ref)
    webServerClient.expectMessage(BadRequest("Not a valid URI"))
  }

  it should "restart and handle subsequent messages" in {
    val webServer = testKit.spawn(WebServer(), "ws2")
    val webServerClient = testKit.createTestProbe[Response]()
    webServer ! Get("http://restart", webServerClient.ref)
    webServer ! Get("http://a-valid-uri", webServerClient.ref)
    webServerClient.expectMessageType[Ok](5 seconds)
  }

  "The Cache actor" should "resume from an error" in {
    val filesystem = testKit.spawn(Filesystem(), "fs")
    val cache = testKit.spawn(Cache(filesystem), "cache")
    val cacheClient = testKit.createTestProbe[Cache.Response]()
    cache ! Find("http://resume", cacheClient.ref)
    cache ! Find("http://a-valid-uri", cacheClient.ref)
    cacheClient.expectMessageType[Hit](5 seconds)
  }

  override protected def afterAll(): Unit = testKit.shutdownTestKit()
}
