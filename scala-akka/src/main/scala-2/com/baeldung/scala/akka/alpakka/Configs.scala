package com.baeldung.scala.akka.alpakka

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer

object Configs {

  implicit val actorSystem: akka.actor.ActorSystem = ActorSystem("Alpakka")
  implicit val materializer: akka.stream.ActorMaterializer = ActorMaterializer()

  val filePath = "vehicle_data.log"

}
