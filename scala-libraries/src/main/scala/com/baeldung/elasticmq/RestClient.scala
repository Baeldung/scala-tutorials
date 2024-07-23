package com.baeldung.elasticmq

import org.elasticmq.rest.sqs.SQSRestServerBuilder
import org.elasticmq.server.ElasticMQServer
import org.elasticmq.server.config.ElasticMQServerConfig

import com.typesafe.config.ConfigFactory

import org.apache.pekko.actor.{Actor, ActorRef, ActorSystem}
import org.apache.pekko.event.LoggingAdapter

implicit val actorSystem: ActorSystem = ActorSystem.create()
implicit val executionContext: concurrent.ExecutionContextExecutor = actorSystem.dispatcher
implicit val m_logger: LoggingAdapter = actorSystem.log

// class QueueActor extends Actor:
//   val messageQueue: mutable.PriorityQueue[InternalMessage]()
//   val awaiting: mutable.PriorityQueue[ActorRef]()

//   def receive = case ReceiveMessages =>


// val result: Future[ActorRef] = flow:
//   (queueManager ? Lookup(name)).apply() match
//     case Some(queueActor) => queueActor
//     case None => (queueManager ? Create(name)).apply()

lazy val server = SQSRestServerBuilder
  .withPort(9325)
  .withInterface("localhost")
  .start()