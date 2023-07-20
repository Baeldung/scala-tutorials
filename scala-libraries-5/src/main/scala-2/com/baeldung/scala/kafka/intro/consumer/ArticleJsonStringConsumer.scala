package com.baeldung.scala.kafka.intro.consumer

import com.baeldung.scala.kafka.intro.common.Article
import com.baeldung.scala.kafka.intro.consumer.common.{
  ConsumerConfig,
  ConsumerUtils,
  JsonStringDeSerializer
}
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.common.serialization.StringDeserializer

import java.util.concurrent.TimeUnit.SECONDS
import scala.concurrent.duration.FiniteDuration
import scala.jdk.javaapi.CollectionConverters.asJavaCollection

object ArticleJsonStringConsumer
  extends App
  with ConsumerUtils[Article]
  with JsonStringDeSerializer[Article] {

  private val (config, topic) = ConsumerConfig.getConfig("kafka-intro.conf")

  private val consumer =
    new KafkaConsumer(config, keyDeSerializer, valueDeSerializer)

  consumer.subscribe(asJavaCollection(List(topic)))

  while (true) {
    val messages = pool(consumer, FiniteDuration(1, SECONDS))
    for ((_, value) <- messages) {
      val article = fromJsonString(value)
      logger.info(
        s"New article received. Title: ${article.title} .  Author: ${article.author.name} "
      )
    }
    consumer.commitAsync()
  }

}
