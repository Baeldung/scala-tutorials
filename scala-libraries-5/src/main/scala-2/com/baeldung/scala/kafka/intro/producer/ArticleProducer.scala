package com.baeldung.scala.kafka.intro.producer

import com.baeldung.scala.kafka.intro._
import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.common.serialization.StringSerializer

import java.util.{Date, UUID}

object ArticleProducer extends App with ProducerUtils[Article] {

  private val (config, topic) =
    ArticleProducerConfig.getConfig("kafka-intro.conf")

  private val keySerializer = new StringSerializer()
  private val valueSerializer = new StringSerializer()

  private val producer =
    new KafkaProducer(config, keySerializer, valueSerializer)

  private val articles = getArticles

  for (article <- articles) {
    produce(producer, topic, article.id, article.toJsonString)
  }
  producer.close()

  private def getArticles: List[Article] = {
    List(
      Article(
        UUID.randomUUID.toString,
        "Introduction to Scala Programming",
        "Scala is a powerful programming language...",
        new Date(),
        Author(1, "John Doe")
      ),
      Article(
        UUID.randomUUID.toString,
        "Introduction to Scala Spire",
        "Spire  is a powerful numerical library...",
        new Date(),
        Author(2, "Jane Doe")
      ),
      Article(
        UUID.randomUUID.toString,
        "Introduction to Kafka",
        "In this article, we'll have an overview of kafka in scala...",
        new Date(),
        Author(3, "Foo Bar")
      )
    )
  }
}
