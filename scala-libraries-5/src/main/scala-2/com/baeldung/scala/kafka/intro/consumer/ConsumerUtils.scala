package com.baeldung.scala.kafka.intro.consumer

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.json.JsonMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import org.apache.kafka.clients.consumer.{ConsumerRecords, KafkaConsumer}
import org.apache.logging.log4j.scala.Logging

import java.util.concurrent.TimeUnit
import scala.concurrent.duration.FiniteDuration
import scala.jdk.CollectionConverters._
import scala.jdk.DurationConverters._
import scala.reflect._

trait ConsumerUtils[T] extends Logging {

  implicit val jsonMapper: JsonMapper = JsonMapper
    .builder()
    .addModule(DefaultScalaModule)
    .enable(DeserializationFeature.FAIL_ON_NULL_CREATOR_PROPERTIES)
    .build()

  def fromJsonString(
    str: String
  )(implicit jsonMapper: JsonMapper, classTag: ClassTag[T]): T = {
    jsonMapper.readValue(str, classTag.runtimeClass).asInstanceOf[T]
  }

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
