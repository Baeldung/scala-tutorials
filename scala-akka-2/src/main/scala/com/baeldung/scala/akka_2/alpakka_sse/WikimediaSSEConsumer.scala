package com.baeldung.scala.akka_2.alpakka_sse

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{HttpRequest, Uri}
import akka.stream.ThrottleMode
import akka.stream.alpakka.sse.scaladsl.EventSource
import akka.stream.scaladsl.{Keep, Sink}

import scala.concurrent.duration.DurationInt

class WikimediaSSEConsumer {

  val wikiMediaUri = Uri("https://stream.wikimedia.org/v2/stream/recentchange")

  def sendRequest(request: HttpRequest)(implicit actorSystem: ActorSystem) =
    Http().singleRequest(request)

  def eventSource(implicit actorSystem: ActorSystem) =
    EventSource(
      uri = wikiMediaUri,
      send = sendRequest,
      initialLastEventId = None,
      retryDelay = 1.second
    )

  def eventsGraph(nrOfSamples: Int)(implicit actorSystem: ActorSystem) =
    eventSource
      .throttle(
        elements = 1,
        per = 1.second,
        maximumBurst = 1,
        ThrottleMode.Shaping
      )
      .take(nrOfSamples)
      .toMat(Sink.seq)(Keep.right)
}
