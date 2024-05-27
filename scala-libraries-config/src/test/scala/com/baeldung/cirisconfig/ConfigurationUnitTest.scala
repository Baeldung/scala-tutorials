package cirisconfig

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import org.scalatest.prop.TableDrivenPropertyChecks
import Configuration.*
import ciris.*
import cats.effect.IO

class ConfigurationUnitTest
  extends AnyFlatSpec
  with Matchers
  with TableDrivenPropertyChecks:

  "postgresConfig" should "be of type ConfigValue[Effect, PostgresConfig]" in {
    postgresConfig shouldBe an[ConfigValue[Effect, PostgresConfig]]
  }

  "postgresConfig2" should "be of type ConfigValue[Effect, PostgresConfig2]" in {
    postgresConfig2 shouldBe an[ConfigValue[Effect, PostgresConfig2]]
  }

  "postgresConfig3" should "be of type ConfigValue[Effect, PostgresConfig3]" in {
    postgresConfig3 shouldBe an[ConfigValue[Effect, PostgresConfig3]]
  }

  "postgresConfig4, " should "be of type ConfigValue[Effect, PostgresConfig4]" in {
    postgresConfig4 shouldBe an[ConfigValue[Effect, PostgresConfig4]]
  }

  "postgresConfig5, postgresConfig6, postgresConfig7" should "be of type IO[Either[ConfigError, PostgresConfig4]]" in {
    forAll(
      Table(
        "postgresConfig567",
        postgresConfig5[IO],
        postgresConfig6[IO],
        postgresConfig7[IO]
      )
    ) { v =>
      v shouldBe an[IO[Either[ConfigError, PostgresConfig4]]]
    }
  }

  "PostgresConfig" should "be of contain type String" in {
    val pconfig = PostgresConfig("u", "p")
    pconfig.username shouldBe an[String]
    pconfig.password shouldBe an[String]
  }

  "PostgresConfig2" should "be of contain type Option[String]" in {
    val pconfig2 = PostgresConfig2(Some("u"), Some("p"))
    pconfig2.username shouldBe an[Option[String]]
    pconfig2.password shouldBe an[Option[String]]
  }

  "PostgresConfig3" should "be of contain type Option[Username] and Option[Password]" in {
    val pconfig3 = PostgresConfig3(Some(Username("u")), Some(Password("p")))
    pconfig3.username shouldBe an[Option[Username]]
    pconfig3.password shouldBe an[Option[Password]]
  }

  "PostgresConfig4" should "be of contain type Username and Password" in {
    val pconfig4 = PostgresConfig4(Username("u"), Password("p"))
    pconfig4.username shouldBe an[Username]
    pconfig4.password shouldBe an[Password]
  }
