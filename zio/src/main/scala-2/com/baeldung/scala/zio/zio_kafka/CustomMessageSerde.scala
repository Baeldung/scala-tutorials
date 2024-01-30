package com.baeldung.scala.zio.zio_kafka

import zio._
import zio.json._
import zio.kafka.serde._

object CustomMessageSerde {
  val key: Serde[Any, String] =
    Serde.string

  val value: Serde[Any, CustomMessage] =
    Serde.string.inmapM[Any, CustomMessage](s =>
      ZIO
        .fromEither(s.fromJson[CustomMessage])
        .mapError(e => new RuntimeException(e))
    )(r => ZIO.succeed(r.toJson))
}
