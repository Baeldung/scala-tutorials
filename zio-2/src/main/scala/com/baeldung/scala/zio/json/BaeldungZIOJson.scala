package com.baeldung.scala.zio.json

import zio.json.*

sealed trait Command
case class Start(timeout: Long) extends Command
case object Stop extends Command

@jsonDiscriminator("type")
sealed trait Command2
case class Start2(timeout: Long) extends Command2
case object Stop2 extends Command2

object Start {
  implicit val encoder: JsonEncoder[Start] = DeriveJsonEncoder.gen[Start]
  implicit val decoder: JsonDecoder[Start] = DeriveJsonDecoder.gen[Start]
}

object Command {
  implicit val encoder: JsonEncoder[Command] = DeriveJsonEncoder.gen[Command]
  implicit val decoder: JsonDecoder[Command] = DeriveJsonDecoder.gen[Command]
}

object Command2 {
  implicit val encoder: JsonEncoder[Command2] = DeriveJsonEncoder.gen[Command2]
  implicit val decoder: JsonDecoder[Command2] = DeriveJsonDecoder.gen[Command2]
}
