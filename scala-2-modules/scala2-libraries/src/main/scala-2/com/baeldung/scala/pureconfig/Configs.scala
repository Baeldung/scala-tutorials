package com.baeldung.pureconfig

import enumeratum._
import pureconfig._
import pureconfig.configurable._
import pureconfig.generic.semiauto._

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import scala.concurrent.duration.FiniteDuration

sealed trait Protocol
object Protocol {
  implicit val protocolConvert: ConfigReader[Protocol] =
    deriveEnumerationReader[Protocol]
  case object Http extends Protocol
  case object Https extends Protocol
}

object impl {
  implicit val localDateConvert: pureconfig.ConfigConvert[java.time.LocalDate] =
    localDateConfigConvert(
      DateTimeFormatter.ISO_DATE
    )
  implicit val localDateTimeConvert
    : pureconfig.ConfigConvert[java.time.LocalDateTime] =
    localDateTimeConfigConvert(
      DateTimeFormatter.ISO_DATE_TIME
    )
}

final case class Port(number: Int) extends AnyVal

final case class KafkaConfig(
  bootstrapServer: String,
  port: Port,
  protocol: Protocol,
  timeout: FiniteDuration
)

final case class GraphiteServer(host: String, port: Port)

final case class GraphiteConf(
  enabled: Boolean,
  servers: Seq[GraphiteServer]
)

/** * Config using Enumeratum based fields **
  */

sealed trait Env extends EnumEntry

object Env extends Enum[Env] {
  case object Prod extends Env
  case object Test extends Env

  override val values = findValues
}

final case class BaseAppConfig(appName: String, baseDate: LocalDate, env: Env)

import enumeratum.EnumEntry._

sealed trait Greeting extends EnumEntry with Snakecase

object Greeting extends Enum[Greeting] {
  val values = findValues
  case object Hello extends Greeting
  case object GoodBye extends Greeting
  case object ShoutGoodBye extends Greeting with Uppercase
}

case class GreetingConf(s: Greeting, e: Greeting)

case class DatabaseConfig(url: String, databaseName: String)

case class NotificationConfig(notificationUrl: String, params: String) {
  def fullURL = s"$notificationUrl?$params"
}
