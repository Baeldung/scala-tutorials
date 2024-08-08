package com.baeldung.elasticmq

import com.typesafe.config.{Config, ConfigFactory}

object ElasticMQConfig:

  private final val config: Config = ConfigFactory.load("elasticmq.conf")

  final val ELASTIC_MQ_ACCESS_KEY: String =
    config.getString("elastic-mq.access-key-id")
  final val ELASTIC_MQ_SECRET_ACCESS_KEY: String =
    config.getString("elastic-mq.secret-access-key")

  final val ELASTIC_MQ_REGION = config.getString("elastic-mq.region")
  final val ELASTIC_MQ_ENDPOINT = config.getString("elastic-mq.endPoint")
