package com.baeldung.scala.kafka.intro.producer

import com.baeldung.scala.kafka.intro.common.Article
import com.baeldung.scala.kafka.intro.producer.common.{
  JsonStringSerializer,
  ProducerConfig,
  ProducerUtils
}
import org.apache.kafka.clients.producer.KafkaProducer

object ArticleJsonStringProducer
  extends App
  with ProducerUtils[Article]
  with JsonStringSerializer[Article] {

  private val (config, topic) = ProducerConfig.getConfig("kafka-intro.conf")

  private val producer =
    new KafkaProducer(config, keySerializer, valueSerializer)

  private val articles = Generator.articles

  for (article <- articles) {
    produce(producer, topic, article.id, article.toJsonString)
  }

  producer.close()
}
