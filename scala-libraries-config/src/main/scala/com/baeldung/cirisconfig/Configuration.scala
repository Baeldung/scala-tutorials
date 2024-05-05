package cirisconfig

import ciris.*
import cats.syntax.all.*
import cats.effect.*
import java.nio.file.Path
import ciris.circe.circeConfigDecoder
import io.circe.Decoder
import cats.effect.Async
import ciris.circe.yaml.circeYamlConfigDecoder

object Configuration:

  final case class PostgresConfig(username: String, password: String)

  def postgresConfig: ConfigValue[Effect, PostgresConfig] =
    (
      env("POSTGRES_USERNAME").as[String],
      env("POSTGRES_PASSWORD").as[String]
    ).parMapN(PostgresConfig.apply)

  final case class PostgresConfig2(
    username: Option[String],
    password: Option[String]
  )

  def postgresConfig2: ConfigValue[Effect, PostgresConfig2] =
    (
      env("POSTGRES_USERNAME").as[String].option,
      env("POSTGRES_PASSWORD").as[String].option
    ).parMapN(PostgresConfig2.apply)

  case class Username(name: String)
  object Username:
    given ConfigDecoder[String, Username] =
      ConfigDecoder[String, String].map(Username.apply)

  case class Password(value: String)
  object Password:
    given ConfigDecoder[String, Password] =
      ConfigDecoder[String, String].map(Password.apply)

  final case class PostgresConfig3(
    username: Option[Username],
    password: Option[Password]
  )

  def postgresConfig3: ConfigValue[Effect, PostgresConfig3] =
    (
      env("POSTGRES_USERNAME").as[Username].option,
      env("POSTGRES_PASSWORD").as[Password].option
    ).parMapN(PostgresConfig3.apply)

  final case class PostgresConfig4(username: Username, password: Password)

  object PostgresConfig4:
    // given ConfigDecoder[String, PostgresConfig4] =
    //   circeConfigDecoder("PostgresConfig4")

    given ConfigDecoder[String, PostgresConfig4] =
      circeYamlConfigDecoder("PostgresConfig4")

    given Decoder[PostgresConfig4] = Decoder.instance { h =>
      for
        username <- h.get[String]("username")
        password <- h.get[String]("password")
      yield PostgresConfig4(Username(username), Password(password))
    }

  val postgresConfig4: ConfigValue[Effect, PostgresConfig4] =
    file(
      Path.of("src/main/resources/postgresConfig.json")
    ).as[PostgresConfig4]

  def postgresConfig5[F[_]: Async]: F[Either[ConfigError, PostgresConfig4]] =
    file(
      Path.of("src/main/resources/postgresConfig.json")
    ).as[PostgresConfig4].attempt[F]

  def postgresConfig6[F[_]: Async]: F[Either[ConfigError, PostgresConfig4]] =
    file(
      Path.of("src/main/resources/postgresConfig.yaml")
    ).as[PostgresConfig4].attempt[F]

  // handling secrets
  case class Password2(value: Secret[String])
  object Password2:
    def apply(value: String) =
      new Password2(Secret(value))

  def postgresConfig7[F[_]: Async]: F[Either[ConfigError, PostgresConfig4]] =
    file(
      Path.of("src/main/resources/missing.yaml")
    ).as[PostgresConfig4]
      .default {
        PostgresConfig4(Username("username"), Password("password"))
      }
      .attempt[F]

object program extends IOApp.Simple:
  import Configuration.*

  override def run: IO[Unit] =
    // blows up application
    // postgresConfig.load[IO].map(println).void

    // handled errors
    // postgresConfig2.load[IO].map(println).void

    // added typeclasses
    // postgresConfig3.load[IO].map(println).void

    // config loading from json file
    // postgresConfig4.load[IO].map(println).void

    // managing errors
    // postgresConfig5[IO].map{config =>
    //   config match
    //     case Right(value) => println(value)
    //     case Left(err) => err.messages.map(println)
    //   }

    // config loading from yaml file
    // postgresConfig6[IO].map{config =>
    //   config match
    //     case Right(value) => println(value)
    //     case Left(err) => err.messages.map(println)
    //   }

    // config loading with secret
    // IO(println(Password2(Secret("password"))))

    // config loading with fallback values
    postgresConfig7[IO].map { config =>
      config match
        case Right(value) => println(value)
        case Left(err)    => err.messages.map(println)
    }
