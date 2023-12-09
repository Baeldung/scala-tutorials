package com.baeldung.scala.zio.zio_kafka

import zio.json._

case class CustomMessage(id: Long, message: String, sender: String)

object CustomMessage {
  implicit val encoder: JsonEncoder[CustomMessage] =
    DeriveJsonEncoder.gen[CustomMessage]

  implicit val decoder: JsonDecoder[CustomMessage] =
    DeriveJsonDecoder.gen[CustomMessage]
}
