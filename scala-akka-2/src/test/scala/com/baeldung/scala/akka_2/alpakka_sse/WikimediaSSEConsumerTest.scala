package com.baeldung.scala.akka_2.alpakka_sse

import akka.actor.ActorSystem
import akka.testkit.TestKit
import org.scalatest.{AsyncWordSpecLike, BeforeAndAfterAll}

class WikimediaSSEConsumerTest
  extends TestKit(ActorSystem("wikimediaSSESpec"))
  with AsyncWordSpecLike
  with BeforeAndAfterAll {

  override def afterAll(): Unit = {
    TestKit.shutdownActorSystem(system)
  }

  "wikimedia sse consumer" should {

    "consume up to defined number of messages" in {
      val wikimediaSSEConsumer = new WikimediaSSEConsumer()
      val eventsFuture = wikimediaSSEConsumer.eventsGraph(10).run()
      eventsFuture.map(events => assert(events.size == 10))
    }
  }
}
