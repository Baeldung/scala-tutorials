package com.baeldung.scala.kafka.intro.producer.common

import com.baeldung.scala.kafka.intro.common.ClientConfig
import com.typesafe.config.Config
import pureconfig.ConfigSource
import pureconfig.generic.auto.exportReader

import java.util

case class ProducerConfig(producer: Config, topic: String)

object ProducerConfig extends ClientConfig {
  def getConfig(resource: String): (util.Map[String, AnyRef], String) = {
    val source =
      ConfigSource.resources(resource).loadOrThrow[ProducerConfig]
    val config = source.producer.asJavaMap
    val topic = source.topic
    (config, topic)
  }
}
