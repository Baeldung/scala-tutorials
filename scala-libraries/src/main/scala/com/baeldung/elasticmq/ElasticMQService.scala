package com.baeldung.elasticmq

import org.elasticmq.rest.sqs.SQSRestServerBuilder
import org.elasticmq.server.ElasticMQServer
import org.elasticmq.server.config.ElasticMQServerConfig

import com.typesafe.config.ConfigFactory

import org.apache.pekko.actor.{Actor, ActorRef, ActorSystem}
import org.apache.pekko.event.LoggingAdapter

import scala.util.{Failure, Success}

object ElasticMQService extends App:

  implicit val actorSystem: ActorSystem = ActorSystem.create()
  implicit val executionContext: concurrent.ExecutionContextExecutor =
    actorSystem.dispatcher
  implicit val m_logger: LoggingAdapter = actorSystem.log

  final val ElasticMQ_URL = s"http://localhost:9324/000000000000/"

  val endpoint = "http://localhost:9325"
  val region = "elasticmq"

  val server = SQSRestServerBuilder
    .withPort(9325)
    .withInterface("localhost")
    .start()

  val elasticMQClient = new SQSAsyncClient(ElasticMQ_URL, region, endpoint)

  val uselessWorkflow =
    for
      _ <- elasticMQClient.createStandardQueue("standardQueueForTest")
      testQueueClient = new SQSAsyncClient(
        ElasticMQ_URL + "standardQueueForTest",
        region,
        endpoint
      )
      _ <- testQueueClient.createFIFOQueue("fifoQueue.fifo")
      _ <- testQueueClient.listQueues()
      _ <- testQueueClient.sendMessage("Hi")
      _ <- testQueueClient.sendMessagesInBatch(
        List("Follow", "Baeldung", "on", "LinkedIn")
      )
      _ <- testQueueClient.receiveMessages(5)
      _ <- testQueueClient.purgeQueue()
    yield ()

  uselessWorkflow
    .andThen(_ => server.stopAndWait())
    .onComplete:
      case Success(_) => m_logger.info("queue created")
      case Failure(exception) =>
        m_logger.error(exception, "exception in uselessWorkflow")
