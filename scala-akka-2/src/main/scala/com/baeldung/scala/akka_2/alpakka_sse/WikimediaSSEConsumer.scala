package com.baeldung.scala.akka_2.alpakka_sse

import akka.NotUsed
import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.sse.ServerSentEvent
import akka.http.scaladsl.model.{HttpRequest, HttpResponse, Uri}
import akka.stream.ThrottleMode
import akka.stream.alpakka.sse.scaladsl.EventSource
import akka.stream.scaladsl.{Keep, RunnableGraph, Sink, Source}

import scala.collection.immutable
import scala.concurrent.Future
import scala.concurrent.duration.DurationInt

class WikimediaSSEConsumer {

  val wikiMediaUri: Uri = Uri(
    "https://stream.wikimedia.org/v2/stream/recentchange"
  )

  def sendRequest(request: HttpRequest)(implicit
    actorSystem: ActorSystem
  ): Future[HttpResponse] = Http().singleRequest(request)

  def eventSource(implicit
    actorSystem: ActorSystem
  ): Source[ServerSentEvent, NotUsed] =
    EventSource(
      uri = wikiMediaUri,
      send = sendRequest,
      initialLastEventId = None,
      retryDelay = 1.second
    )

  def eventsGraph(
    nrOfSamples: Int
  )(implicit
    actorSystem: ActorSystem
  ): RunnableGraph[Future[Seq[ServerSentEvent]]] =
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
