package services

import java.util.Date
import com.typesafe.config.Config

import javax.inject.Inject
import play.api.{ConfigLoader, Configuration}

import java.text.SimpleDateFormat
import java.time.LocalDateTime
import scala.util.Try

object ISO8601DateConfigLoader {
  implicit val iso8601DateConfigLoader: ConfigLoader[Date] = {
    ConfigLoader(_.getString)
      .map[Date](new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(_))
  }
}

case class PlayerInfo(
  name: String,
  email: String,
  age: Int,
  signUpDate: Date,
  twitterHandle: Option[String] = None
)

object PlayerInfo {
  implicit val playerInfoConfigLoader: ConfigLoader[PlayerInfo] =
    (rootConfig: Config, path: String) => {
      val config = rootConfig.getConfig(path)
      new PlayerInfo(
        config.getString("name"),
        config.getString("email"),
        config.getInt("age"),
        new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
          .parse(config.getString("signUpDate")),
        Try(config.getString("twitterHandle")).toOption
      )
    }
}

class MyService @Inject() (configuration: Configuration) {

  def getPlayerInfoVersion1: PlayerInfo = {
    PlayerInfo(
      configuration.get[String]("player.name"),
      configuration.get[String]("player.email"),
      configuration.get[Int]("player.age"),
      configuration.get[Date]("player.signUpDate")(
        ISO8601DateConfigLoader.iso8601DateConfigLoader
      ),
      configuration.get[Option[String]]("player.twitterHandle")
    )
  }

  def getPlayerInfoVersion2: PlayerInfo = {
    configuration.get[PlayerInfo]("player")
  }
}
