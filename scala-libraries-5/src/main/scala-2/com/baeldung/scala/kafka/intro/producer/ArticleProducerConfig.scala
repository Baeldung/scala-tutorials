package com.baeldung.scala.kafka.intro.producer

import com.baeldung.scala.kafka.intro.ClientConfig
import com.typesafe.config.Config
import pureconfig.ConfigSource
import pureconfig.generic.auto.exportReader

import java.util

case class ArticleProducerConfig(producer: Config, topic: String)

object ArticleProducerConfig extends ClientConfig {
  def getConfig(resource: String): (util.Map[String, AnyRef], String) = {
    val source =
      ConfigSource.resources(resource).loadOrThrow[ArticleProducerConfig]
    val config = source.producer.asJavaMap
    val topic = source.topic
    (config, topic)
  }
}
