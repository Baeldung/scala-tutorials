package com.baeldung.scala.kafka.intro.consumer.common

import org.apache.kafka.clients.consumer.{ConsumerRecords, KafkaConsumer}
import org.apache.logging.log4j.scala.Logging

import java.util.concurrent.TimeUnit
import scala.concurrent.duration.FiniteDuration
import scala.jdk.CollectionConverters._
import scala.jdk.DurationConverters._

trait ConsumerUtils[T] extends Logging {
  def pool[K, V](
    consumer: KafkaConsumer[K, V],
    timeout: FiniteDuration = FiniteDuration(5, TimeUnit.SECONDS)
  ): Iterable[(K, V)] = {
    val records: ConsumerRecords[K, V] =
      consumer.poll(new ScalaDurationOps(timeout).toJava)
    val messages = records.asScala.map(record => {
      logger.debug(
        s"received record from topic ${record.topic}. Key:  ${record.key} value: ${record.value.toString}"
      )
      (record.key, record.value)
    })
    messages
  }
}
