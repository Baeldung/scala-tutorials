package com.baeldung.scala.kafka.intro.consumer

import com.baeldung.scala.kafka.intro.common.Article
import com.baeldung.scala.kafka.intro.consumer.common.{
  AvroDeSerializer,
  ConsumerConfig,
  ConsumerUtils
}
import com.sksamuel.avro4s.RecordFormat
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.common.serialization.{Deserializer, StringDeserializer}

import java.util.concurrent.TimeUnit.SECONDS
import scala.concurrent.duration.FiniteDuration
import scala.jdk.javaapi.CollectionConverters.asJavaCollection

object ArticleAvroConsumer
  extends App
  with ConsumerUtils[Article]
  with AvroDeSerializer {

  private val (config, topic) =
    ConsumerConfig.getConfig("kafka-intro-avro.conf")

  val keyDeSerializer: StringDeserializer = new StringDeserializer()

  implicit lazy val Valueformat: RecordFormat[Article] = RecordFormat[Article]
  val valueDeSerializer: Deserializer[Article] = deserializer[Article]
  valueDeSerializer.configure(config, false)

  private val consumer =
    new KafkaConsumer(config, keyDeSerializer, valueDeSerializer)

  consumer.subscribe(asJavaCollection(List(topic)))

  while (true) {
    val messages = pool(consumer, FiniteDuration(1, SECONDS))
    for ((_, article) <- messages) {
      logger.info(
        s"New article received. Title: ${article.title} .  Author: ${article.author.name} "
      )
    }
    consumer.commitAsync()
  }

}
