package com.baeldung.scala.zio.zio_kafka.stream

import com.baeldung.scala.zio.zio_kafka.{CustomMessage, CustomMessageSerde}
import org.apache.kafka.clients.producer.ProducerRecord
import zio._
import zio.kafka.consumer._
import zio.ZIOAppDefault
import zio.kafka.producer.Producer
import zio.stream.ZStream

object KafkaStreamWorkflowApp extends ZIOAppDefault {

  private val BOOTSTRAP_SERVERS = List("localhost:9092")
  private val KAFKA_TOPIC = "baeldung"

  val producer: ZLayer[Any, Throwable, Producer] =
    KafkaStreamProducer.producerLayer(BOOTSTRAP_SERVERS)
  val consumer: ZLayer[Any, Throwable, Consumer] =
    KafkaStreamConsumer.consumerLayer(
      BOOTSTRAP_SERVERS,
      "baeldung-consumer-group"
    )

  override def run = {
    val prod: ZStream[Producer, Throwable, Nothing] =
      ZStream
        .repeat("key")
        .schedule(Schedule.spaced(1.second))
        .map(key =>
          new ProducerRecord(
            KAFKA_TOPIC,
            key,
            CustomMessage(1, "Hello", "Baeldung")
          )
        )
        .via(
          Producer.produceAll(CustomMessageSerde.key, CustomMessageSerde.value)
        )
        .drain

    val cons: ZStream[Consumer, Throwable, Nothing] =
      Consumer
        .plainStream(
          Subscription.topics(KAFKA_TOPIC),
          CustomMessageSerde.key,
          CustomMessageSerde.value
        )
        .tap(record =>
          Console
            .printLine(
              s"Consumed message with key: ${record.key} and value: ${record.value}"
            )
            .orDie
        )
        .map(_.offset)
        .aggregateAsync(Consumer.offsetBatches)
        .mapZIO(_.commit)
        .drain

    (prod merge cons).runDrain.provide(producer, consumer)
  }

}
