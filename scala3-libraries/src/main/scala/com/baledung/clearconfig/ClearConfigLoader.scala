package com.baeldung.clearconfig
import japgolly.clearconfig._
import cats.implicits._
import cats.Id
import cats.catsInstancesForId
import scala.concurrent.duration.FiniteDuration
import scala.concurrent.duration._
import japgolly.clearconfig.internals.Source
import japgolly.clearconfig.internals.Report

final case class CCNotificationConfig(
  notificationUrl: String,
  params: String,
  intervalInMin: Option[Int]
)

object CCNotificationConfig {
  def notificationConfig: ConfigDef[CCNotificationConfig] =
    (
      ConfigDef.need[String]("notificationUrl"),
      ConfigDef.need[String]("params"),
      ConfigDef.get[Int]("intervalInMin")
    ).mapN(apply)

  def configSources: ConfigSources[Id] =
    ConfigSource.environment[Id].caseInsensitive >
      ConfigSource
        .propFileOnClasspath[Id]("/notification-prod.conf", optional = true) >
      ConfigSource
        .propFileOnClasspath[Id]("/notification.conf", optional = false) >
      ConfigSource.system[Id]

}

sealed trait Protocol
object Protocol {
  case object Http extends Protocol
  case object Https extends Protocol
  def resolve(protocol: String): Protocol = {
    protocol.toLowerCase match {
      case "http"  => Http
      case "https" => Https
      case any     => throw new RuntimeException(s"`$any` is not a valid protocol")
    }
  }
}
given protocolParser: ConfigValueParser[Protocol] =
  ConfigValueParser
    .oneOf[Protocol]("http" -> Protocol.Http, "https" -> Protocol.Https)
    .preprocessValue(_.toLowerCase)

final case class KafkaConfig(
  port: Int,
  bootstrapServer: String,
  protocol: Protocol,
  timeout: FiniteDuration
)
object KafkaConfig {
  val kafkaConfigDef: ConfigDef[KafkaConfig] = (
    ConfigDef.need[Int]("port"),
    ConfigDef.need[String]("bootstrap-server"),
    ConfigDef.need[String]("protocol"),
    ConfigDef.need[FiniteDuration]("timeout")
  ).mapN {
    case (port, server, protocol, timeout) =>
      KafkaConfig(port, server, Protocol.resolve(protocol), timeout)
  }
}

object ClearConfigReader extends App {
  val notificationConfig: CCNotificationConfig =
    CCNotificationConfig.notificationConfig
      .run(CCNotificationConfig.configSources)
      .getOrDie()

  val kafkaConfigSource: ConfigSources[Id] =
    ConfigSource.propFileOnClasspath[Id]("kafka.conf", optional = false)

  val kafkaConfig: KafkaConfig =
    KafkaConfig.kafkaConfigDef
      .withPrefix("kafka.")
      .run(kafkaConfigSource)
      .getOrDie()

  // println(notificationConfig)
  // println(kafkaConfig)


  val (notificationConfigResult, notificationReport) = CCNotificationConfig.notificationConfig
  .withReport.run(CCNotificationConfig.configSources).getOrDie()

  // println(notificationReport.full)
  val reducedReport = notificationReport.mapUnused(_.withoutSources(ConfigSourceName.environment, ConfigSourceName.system)).full
  println(reducedReport)

}
