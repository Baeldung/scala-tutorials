package com.baeldung.scala.kafka.intro.common

import com.typesafe.config.Config
import pureconfig.ConfigSource
import pureconfig.generic.auto.exportReader

import java.util

case class SerdeConfig(serde: Config)

object SerdeConfig extends ClientConfig {
  def getConfig(resource: String): util.Map[String, AnyRef] = {
    val source =
      ConfigSource.resources(resource).loadOrThrow[SerdeConfig]
    val serde = source.serde.asJavaMap
    serde
  }
}
