val scalaV = ScalaVersions.scala2Version
val scala3Version = ScalaVersions.scala3Version
ThisBuild / scalaVersion := scala3Version
ThisBuild / organization := "com.baeldung"
ThisBuild / organizationName := "core-scala"
val jUnitInterface = "com.github.sbt" % "junit-interface" % "0.13.3" % "test"
val catsEffect = "org.typelevel" %% "cats-effect" % "3.5.4"
val catEffectTest = "org.typelevel" %% "cats-effect-testkit" % "3.5.4" % Test
val scalaReflection = "org.scala-lang" % "scala-reflect" % scalaV
val logback = "ch.qos.logback" % "logback-classic" % "1.5.6"
val embedMongoVersion = "4.15.0"
val AkkaVersion = "2.8.5"
val AkkaHttpVersion = "10.5.0"

val scalaTestDeps = Seq(
  "org.scalatest" %% "scalatest" % "3.2.19" % Test,
  "org.scalatest" %% "scalatest-shouldmatchers" % "3.2.19" % Test,
  "org.scalatest" %% "scalatest-wordspec" % "3.2.19" % Test,
  "org.scalatest" %% "scalatest-flatspec" % "3.2.19" % Test
)

val scalaMock = "org.scalamock" %% "scalamock" % "6.0.0" % Test
val zioVersion = "2.1.5"

lazy val scala_core = (project in file("scala-core-modules/scala-core"))
  .settings(
    name := "scala-core",
    scalaVersion := scala3Version,
    libraryDependencies ++=
      Seq(
        jUnitInterface
      ) ++ scalaTestDeps
  )

lazy val scala_core_2 = (project in file("scala-core-modules/scala-core-2"))
  .settings(
    name := "scala-core-2",
    libraryDependencies ++= scalaTestDeps,
    libraryDependencies += jUnitInterface,
    scalaVersion := scala3Version
  )

val scalaXmlDep = "org.scala-lang.modules" %% "scala-xml" % "2.3.0"

lazy val scala_core_3 = (project in file("scala-core-modules/scala-core-3"))
  .settings(
    name := "scala-core-3",
    scalaVersion := scala3Version,
    libraryDependencies ++= scalaTestDeps,
    libraryDependencies += jUnitInterface,
    libraryDependencies += scalaXmlDep
  )

lazy val scala2_core = (project in file("scala-2-modules/scala2-core"))
  .settings(
    name := "scala2-core",
    libraryDependencies ++= scalaTestDeps,
    libraryDependencies += jUnitInterface,
    libraryDependencies += scalaReflection,
    libraryDependencies += scalaXmlDep,
    libraryDependencies += "com.github.scopt" %% "scopt" % "4.1.0",
    libraryDependencies += "org.rogach" %% "scallop" % "5.1.0",
    libraryDependencies += "org.backuity.clist" %% "clist-core" % "3.5.1",
    libraryDependencies += "org.backuity.clist" %% "clist-macros" % "3.5.1" % "provided",
    libraryDependencies += "args4j" % "args4j" % "2.37",
    libraryDependencies += "org.scala-lang" % "scala-compiler" % scalaVersion.value % "test",
    libraryDependencies += catsEffect
  )

lazy val scala_core_4 = (project in file("scala-core-modules/scala-core-4"))
  .settings(
    name := "scala-core-4",
    scalaVersion := scala3Version,
    libraryDependencies ++= scalaTestDeps,
    libraryDependencies += jUnitInterface,
    libraryDependencies += scalaReflection
  )

lazy val scala_core_5 = (project in file("scala-core-modules/scala-core-5"))
  .settings(
    name := "scala-core-5",
    scalaVersion := scala3Version,
    libraryDependencies ++= scalaTestDeps,
    libraryDependencies += jUnitInterface
  )

lazy val scala_core_6 = (project in file("scala-core-modules/scala-core-6"))
  .settings(
    name := "scala-core-6",
    scalaVersion := scala3Version,
    libraryDependencies ++= scalaTestDeps,
    libraryDependencies += jUnitInterface
  )

lazy val scala_core_7 = (project in file("scala-core-modules/scala-core-7"))
  .settings(
    name := "scala-core-7",
    scalaVersion := scala3Version,
    libraryDependencies ++= scalaTestDeps,
    libraryDependencies += jUnitInterface
  )

lazy val scala_core_8 = (project in file("scala-core-modules/scala-core-8"))
  .settings(
    name := "scala-core-8",
    libraryDependencies ++= scalaTestDeps,
    scalaVersion := scala3Version,
    libraryDependencies += "org.scala-lang.modules" %% "scala-java8-compat" % "1.0.2",
    libraryDependencies += "com.typesafe" % "config" % "1.2.1"
    // scalacOptions += "-Ymacro-debug-lite"
  )

lazy val scala_core_9 = (project in file("scala-core-modules/scala-core-9"))
  .settings(
    name := "scala-core-9",
    libraryDependencies ++= scalaTestDeps,
    scalaVersion := scala3Version,
    libraryDependencies += "org.scalatestplus" %% "scalacheck-1-17" % "3.2.18.0" % Test
  )

lazy val scala_core_numbers =
  (project in file("scala-core-modules/scala-core-numbers"))
    .settings(
      name := "scala-core-numbers",
      libraryDependencies ++= scalaTestDeps,
      scalaVersion := scala3Version,
      libraryDependencies += "org.scalatestplus" %% "scalacheck-1-17" % "3.2.18.0" % Test
    )

lazy val scala_core_io = (project in file("scala-core-modules/scala-core-io"))
  .settings(
    name := "scala-core-io",
    scalaVersion := scala3Version,
    libraryDependencies ++= scalaTestDeps,
    libraryDependencies += jUnitInterface
  )

lazy val scala_core_oop = (project in file("scala-core-modules/scala-core-oop"))
  .settings(
    name := "scala-core-oop",
    scalaVersion := scala3Version,
    libraryDependencies ++=
      Seq(catsEffect, jUnitInterface) ++ scalaTestDeps
  )

lazy val scala_core_fp = (project in file("scala-core-modules/scala-core-fp"))
  .settings(
    name := "scala-core-fp",
    scalaVersion := scala3Version,
    libraryDependencies ++=
      Seq(catsEffect, jUnitInterface) ++ scalaTestDeps
  )

lazy val scala_core_dates =
  (project in file("scala-core-modules/scala-core-dates"))
    .settings(
      name := "scala-core-dates",
      scalaVersion := scala3Version,
      libraryDependencies ++= scalaTestDeps,
      libraryDependencies += "joda-time" % "joda-time" % "2.12.7",
      libraryDependencies += "com.github.nscala-time" %% "nscala-time" % "2.32.0",
      libraryDependencies += "com.typesafe" % "config" % "1.4.3"
    )

lazy val scala_lang = (project in file("scala-lang-modules/scala-lang"))
  .settings(
    name := "scala-lang",
    scalaVersion := scala3Version,
    libraryDependencies ++=
      Seq(jUnitInterface) ++ scalaTestDeps
  )

lazy val scala_lang_2 = (project in file("scala-lang-modules/scala-lang-2"))
  .settings(
    name := "scala-lang-2",
    scalaVersion := scala3Version,
    libraryDependencies ++=
      Seq(jUnitInterface) ++ scalaTestDeps
  )

val scalaParColDep =
  "org.scala-lang.modules" %% "scala-parallel-collections" % "1.0.4"

lazy val scala_core_collections =
  (project in file("scala-core-collections-modules/scala-core-collections"))
    .settings(
      name := "scala-core-collections",
      scalaVersion := scala3Version,
      libraryDependencies ++= Seq(
        scalaParColDep
      ) ++ scalaTestDeps
    )

lazy val scala_core_collections_2 =
  (project in file("scala-core-collections-modules/scala-core-collections-2"))
    .settings(
      name := "scala-core-collections-2",
      scalaVersion := scala3Version,
      libraryDependencies ++= scalaTestDeps
    )

lazy val scala_core_collections_3 =
  (project in file("scala-core-collections-modules/scala-core-collections-3"))
    .settings(
      scalaVersion := scala3Version,
      libraryDependencies ++= scalaTestDeps
    )

lazy val scala_core_collections_4 =
  (project in file("scala-core-collections-modules/scala-core-collections-4"))
    .settings(
      scalaVersion := scala3Version,
      libraryDependencies ++= scalaTestDeps
    )

lazy val scala_core_map =
  (project in file("scala-core-collections-modules/scala-core-map"))
    .settings(
      scalaVersion := scala3Version,
      libraryDependencies ++= scalaTestDeps
    )

lazy val scala_test = (project in file("scala-test"))
  .settings(
    name := "scala-test",
    scalaVersion := scala3Version,
    libraryDependencies ++=
      Seq(
        scalaTestPlusMockito,
        jUnitInterface
      ) ++ scalaTestDeps
  )

lazy val scala_test_2 = (project in file("scala-test-2"))
  .settings(
    name := "scala-test-2",
    scalaVersion := scala3Version,
    libraryDependencies ++= scalaTestDeps
  )

val embeddedMongo =
  "de.flapdoodle.embed" % "de.flapdoodle.embed.mongo" % embedMongoVersion

val akkaTypedTestkit =
  "com.typesafe.akka" %% "akka-actor-testkit-typed" % AkkaVersion % "it,test"
val akkaStreamDep = "com.typesafe.akka" %% "akka-stream" % AkkaVersion
val akkaHttpDep = "com.typesafe.akka" %% "akka-http" % AkkaHttpVersion
val akkaActorTyped = "com.typesafe.akka" %% "akka-actor-typed" % AkkaVersion
val akkaHttpTestkitDep =
  "com.typesafe.akka" %% "akka-http-testkit" % AkkaHttpVersion

lazy val scala_akka_dependencies: Seq[ModuleID] = Seq(
  akkaActorTyped,
  akkaTypedTestkit,
  "com.lightbend.akka" %% "akka-stream-alpakka-mongodb" % "5.0.0",
  akkaStreamDep,
  "org.mongodb.scala" %% "mongo-scala-driver" % "5.1.1",
  "com.lightbend.akka" %% "akka-stream-alpakka-file" % "5.0.0",
  jUnitInterface,
  embeddedMongo % Test,
  akkaHttpDep
) ++ scalaTestDeps

lazy val scala_test_junit4 = (project in file("scala-test-junit4"))
  .settings(
    name := "scala-test-junit4",
    scalaVersion := scala3Version,
    libraryDependencies ++=
      Seq(
        "org.scalatestplus" %% "junit-4-13" % "3.2.19.0" % Test,
        jUnitInterface
      )
  )

lazy val scala_test_junit5 = (project in file("scala-test-junit5"))
  .settings(
    name := "scala-test-junit5",
    scalaVersion := scala3Version
  )

lazy val scala_akka = (project in file("scala-akka"))
  .configs(IntegrationTest)
  .settings(
    name := "scala-akka",
    libraryDependencies ++= scala_akka_dependencies ++ Seq(
      "ch.qos.logback" % "logback-classic" % "1.4.11", // scala-steward:off
      embeddedMongo % "it,compile"
    ) ++ scalaTestDeps.map(_.withConfigurations(Some("it,test"))),
    Defaults.itSettings
  )

lazy val scala_akka_2 = (project in file("scala-akka-2"))
  .enablePlugins(AkkaGrpcPlugin)
  .configs(IntegrationTest)
  .settings(
    name := "scala-akka-2",
    Defaults.itSettings,
    libraryDependencies ++= Seq(
      akkaActorTyped,
      akkaStreamDep,
      akkaHttpDep,
      "com.typesafe.akka" %% "akka-discovery" % AkkaVersion,
      "com.typesafe.akka" %% "akka-http-spray-json" % AkkaHttpVersion,
      akkaHttpTestkitDep,
      "com.lightbend.akka" %% "akka-stream-alpakka-csv" % "5.0.0",
      "com.lightbend.akka" %% "akka-stream-alpakka-sse" % "5.0.0",
      "com.typesafe.akka" %% "akka-persistence-typed" % AkkaVersion,
      akkaTypedTestkit,
      akkaHttpTestkitDep % "it,test",
      "com.typesafe.akka" %% "akka-stream-testkit" % AkkaVersion % Test
    ) ++ scalaTestDeps.map(_.withConfigurations(Some("it,test")))
  )

lazy val scala_akka_3 = (project in file("scala-akka-3"))
  .enablePlugins(AkkaGrpcPlugin)
  .configs(IntegrationTest)
  .settings(
    name := "scala-akka-3",
    Defaults.itSettings,
    libraryDependencies ++= Seq(
      "com.typesafe.akka" %% "akka-stream" % AkkaVersion,
      "com.typesafe.akka" %% "akka-discovery" % AkkaVersion,
      "com.typesafe.akka" %% "akka-stream-testkit" % AkkaVersion % Test,
      akkaActorTyped,
      akkaStreamDep,
      akkaTypedTestkit
    ) ++ scalaTestDeps.map(_.withConfigurations(Some("it,test")))
  )

val monocleVersion = "2.1.0"
val slickVersion = "3.5.1"
val shapelessVersion = "2.3.12"
val scalazVersion = "7.3.8"
val fs2Version = "3.10.2"
val reactiveMongo = "1.1.0-RC12"

lazy val scala2_libraries =
  (project in file("scala-2-modules/scala2-libraries"))
    .configs(IntegrationTest)
    .settings(
      name := "scala2-libraries",
      scalaVersion := scalaV,
      libraryDependencies ++= scalaTestDeps
        .map(_.withConfigurations(Some("it,test"))),
      resolvers += "Kafka avro serializer" at "https://packages.confluent.io/maven",
      libraryDependencies ++= Seq(
        "com.github.julien-truffaut" %% "monocle-core" % monocleVersion,
        "com.github.julien-truffaut" %% "monocle-macro" % monocleVersion,
        "com.github.julien-truffaut" %% "monocle-law" % monocleVersion % "test",
        "com.chuusai" %% "shapeless" % shapelessVersion,
        "junit" % "junit" % "4.13.2" % Test,
        logback % Test,
        akkaActorTyped,
        akkaStreamDep,
        "com.github.cb372" %% "scalacache-core" % "0.28.0",
        "com.github.cb372" %% "scalacache-guava" % "0.28.0",
        "com.github.cb372" %% "scalacache-cats-effect" % "0.28.0",
        "com.github.cb372" %% "scalacache-caffeine" % "0.28.0",
        enumeratumDep,
        "io.monix" %% "monix" % monixVersion,
        pureConfigDep,
        "com.github.pureconfig" %% "pureconfig-enumeratum" % "0.17.7",
        "com.typesafe" % "config" % "1.4.3",
        "org.scala-lang.modules" %% "scala-async" % "1.0.1",
        "com.clever-cloud.pulsar4s" %% "pulsar4s-core" % "2.9.1",
        "com.clever-cloud.pulsar4s" %% "pulsar4s-jackson" % "2.9.1",
        "org.testcontainers" % "pulsar" % "1.19.8" % IntegrationTest,
        "org.apache.kafka" % "kafka-clients" % kafkaVersion,
        "com.fasterxml.jackson.core" % "jackson-databind" % jackSonVersion,
        "com.fasterxml.jackson.datatype" % "jackson-datatype-jsr310" % jackSonVersion,
        "com.fasterxml.jackson.module" %% "jackson-module-scala" % jackSonVersion,
        "com.sksamuel.avro4s" %% "avro4s-core" % avro4sVersion,
        "io.confluent" % "kafka-avro-serializer" % kafkaAvroSerializer,
        log4jApiScalaDep,
        "org.apache.logging.log4j" % "log4j-core" % log4jVersion % Runtime
      ),
      libraryDependencies ++= Seq(
        "com.typesafe.slick" %% "slick" % slickVersion,
        "com.h2database" % "h2" % "2.2.224"
      ),
      scalacOptions += "-Xasync",
      Defaults.itSettings
    )

val circeVersion = "0.14.9"
val monixVersion = "3.4.1"
val elastic4sVersion = "8.13.1"
val sparkVersion = "3.5.1"

val sparkCoreDep = "org.apache.spark" %% "spark-core" % sparkVersion
val sparkSqlDep = "org.apache.spark" %% "spark-sql" % sparkVersion

val enumeratumDep = "com.beachape" %% "enumeratum" % "1.7.4"
val circeDep = "io.circe" %% "circe-generic" % circeVersion
val circeParserDep = "io.circe" %% "circe-parser" % circeVersion

lazy val scala_libraries = (project in file("scala-libraries"))
  .configs(IntegrationTest)
  .settings(
    name := "scala-libraries",
    scalaVersion := scala3Version,
    libraryDependencies ++= scalaTestDeps
      .map(_.withConfigurations(Some("it,test"))),
    libraryDependencies ++= Seq(
      "io.circe" %% "circe-core" % circeVersion,
      circeDep,
      circeParserDep,
      "com.softwaremill.retry" %% "retry" % "0.3.6",
      log4jApiScalaDep,
      "org.apache.logging.log4j" % "log4j-core" % "2.23.1" % Runtime,
      "com.typesafe.scala-logging" %% "scala-logging" % "3.9.5",
      "software.amazon.awssdk" % "s3" % "2.25.9",
      "com.github.seratch" %% "awscala" % "0.9.2"
    ),
    libraryDependencies ++= Seq(
      "org.playframework" %% "play-slick" % LibraryVersions.playSlickVersion,
      "org.postgresql" % "postgresql" % "42.7.3"
    ),
    dependencyOverrides := Seq(
      "com.typesafe.akka" %% "akka-protobuf-v3" % AkkaVersion,
      akkaStreamDep,
      "com.typesafe.akka" %% "akka-serialization-jackson" % AkkaVersion
    ),
    libraryDependencies ++= Seq(
      akkaTypedTestkit,
      "com.lihaoyi" %% "requests" % "0.8.3"
    ),
    libraryDependencies ++= Seq(
      "nl.gn0s1s" %% "elastic4s-client-esjava" % elastic4sVersion,
      "nl.gn0s1s" %% "elastic4s-core" % elastic4sVersion,
      logback
    ),
    Defaults.itSettings
  )

val http4sBlaze = "0.23.16"
val http4sVersion = "0.23.27"
val osLibVersion = "0.10.2"

val osLibDep = "com.lihaoyi" %% "os-lib" % osLibVersion

val log4jApiScalaDep =
  "org.apache.logging.log4j" %% "log4j-api-scala" % "13.1.0"

val munitDep = "org.scalameta" %% "munit" % "1.0.0" % Test

lazy val scala_libraries_os = (project in file("scala-libraries-os"))
  .settings(
    name := "scala-libraries",
    scalaVersion := scala3Version,
    libraryDependencies ++= scalaTestDeps,
    libraryDependencies ++= Seq(
      log4jApiScalaDep,
      "org.apache.logging.log4j" % "log4j-core" % "2.23.1" % Runtime
    ),
    libraryDependencies += osLibDep
  )

lazy val redis_intro =
  (project in file("scala-libraries-standalone/redis-intro"))
    .configs(IntegrationTest)
    .settings(
      name := "redis-intro",
      scalaVersion := scala3Version,
      libraryDependencies ++= scalaTestDeps
        .map(_.withConfigurations(Some("it,test"))),
      libraryDependencies ++= Seq(
        "redis.clients" % "jedis" % "5.1.3",
        "com.fasterxml.jackson.module" %% "jackson-module-scala" % "2.14.1",
        "org.scalatestplus" %% "mockito-3-4" % "3.2.10.0" % "it"
      ),
      Defaults.itSettings
    )

lazy val tapir = (project in file("scala-libraries-standalone/tapir"))
  .settings(
    name := "tapir",
    scalaVersion := scala3Version
  )

lazy val refined = (project in file("scala-libraries-standalone/refined"))
  .settings(
    name := "refined",
    scalaVersion := scalaV,
    libraryDependencies += "eu.timepit" %% "refined" % "0.11.2",
    libraryDependencies ++= scalaTestDeps
  )

val spireVersion = "0.18.0"
val kafkaVersion = "7.6.1-ce"
val pureconfigVersion = "0.17.7"
val jackSonVersion = "2.17.1"
val log4jApiScalaVersion = "13.1.0"
val log4jVersion = "2.20.0"
val avro4sVersion = "4.1.2"
val kafkaAvroSerializer = "7.6.1"

val pureConfigDep = "com.github.pureconfig" %% "pureconfig" % pureconfigVersion

lazy val scala_libraries_fp = (project in file("scala-libraries-fp"))
  .settings(
    name := "scala-libraries-fp",
    scalaVersion := scala3Version,
    libraryDependencies ++= scalaTestDeps,
    libraryDependencies ++= Seq(
      "co.fs2" %% "fs2-core" % fs2Version,
      "co.fs2" %% "fs2-io" % fs2Version,
      "org.typelevel" %% "cats-core" % "2.12.0",
      "org.http4s" %% "http4s-dsl" % http4sVersion,
      "org.http4s" %% "http4s-blaze-server" % http4sBlaze,
      "org.http4s" %% "http4s-blaze-client" % http4sBlaze,
      catEffectTest,
      "org.typelevel" %% "cats-effect-testing-scalatest" % "1.5.0" % Test,
      "org.scalaz" %% "scalaz-core" % scalazVersion,
      "junit" % "junit" % "4.13.2" % Test,
      "org.typelevel" %% "spire" % spireVersion
    )
  )

lazy val scala_libraries_testing = (project in file("scala-libraries-testing"))
  .configs(IntegrationTest)
  .settings(
    name := "scala-libraries-testing",
    scalaVersion := scala3Version,
    testFrameworks += new TestFramework("utest.runner.Framework"),
    libraryDependencies ++= scalaTestDeps
      .map(_.withConfigurations(Some("it,test"))),
    libraryDependencies ++= Seq(
      "org.scalacheck" %% "scalacheck" % "1.18.0" % Test,
      scalaMock,
      "com.lihaoyi" %% "utest" % "0.8.3" % "test",
      munitDep,
      "com.amazonaws" % "aws-java-sdk-s3" % "1.12.756" % IntegrationTest,
      "com.dimafeng" %% "testcontainers-scala-scalatest" % "0.41.3" % IntegrationTest,
      "com.dimafeng" %% "testcontainers-scala-localstack-v2" % "0.41.3" % IntegrationTest,
      "software.amazon.awssdk" % "s3" % "2.26.15"
    ),
    Defaults.itSettings,
    IntegrationTest / fork := true
  )

lazy val scala_libraries_persistence =
  (project in file("scala-libraries-persistence"))
    .configs(IntegrationTest)
    .settings(
      name := "scala-libraries-persistence",
      scalaVersion := scala3Version,
      Defaults.itSettings,
      libraryDependencies ++= scalaTestDeps
        .map(_.withConfigurations(Some("it,test"))),
      libraryDependencies ++= Seq(
        "com.typesafe.slick" %% "slick" % slickVersion,
        "com.h2database" % "h2" % "2.2.224",
        "org.tpolecat" %% "skunk-core" % "0.6.4",
        doobieCore,
        doobiePGDep,
        "org.reactivemongo" %% "reactivemongo" % reactiveMongo,
        "org.reactivemongo" %% "reactivemongo-akkastream" % reactiveMongo,
        "de.flapdoodle.embed" % "de.flapdoodle.embed.mongo" % embedMongoVersion % IntegrationTest,
        logback
      )
    )

lazy val scala_libraries_config = (project in file("scala-libraries-config"))
  .settings(
    name := "scala-libraries-config",
    scalaVersion := scala3Version,
    libraryDependencies ++= scalaTestDeps,
    libraryDependencies ++= Seq(
      "com.typesafe" % "config" % "1.4.3",
      munitDep,
      "com.github.japgolly.clearconfig" %% "core" % "3.1.0",
      catsEffect,
      "io.circe" %% "circe-yaml" % "0.15.2",
      circeDep,
      circeParserDep
    ),
    libraryDependencies += "is.cir" %% "ciris" % "3.6.0",
    libraryDependencies += "is.cir" %% "ciris-circe" % "3.6.0",
    libraryDependencies += "is.cir" %% "ciris-circe-yaml" % "3.6.0"
  )

lazy val scala_strings = (project in file("scala-strings"))
  .settings(
    name := "scala-strings",
    scalaVersion := scala3Version,
    libraryDependencies ++= scalaTestDeps,
    libraryDependencies += jUnitInterface,
    libraryDependencies += scalaParColDep,
    libraryDependencies += "org.scalatestplus" %% "scalacheck-1-17" % "3.2.18.0" % Test
  )

val scalaTestPlusMockito =
  "org.scalatestplus" %% "mockito-5-10" % "3.2.18.0" % Test

lazy val scala_design_patterns = (project in file("scala-design-patterns"))
  .settings(
    name := "scala-design-patterns",
    scalaVersion := scala3Version,
    libraryDependencies ++= scalaTestDeps,
    libraryDependencies += scalaTestPlusMockito,
    libraryDependencies += "com.novocode" % "junit-interface" % "0.11" % Test
  )

lazy val scala_lang_3 =
  (project in file("scala-lang-modules/scala-lang-3")).settings(
    libraryDependencies ++= scalaTestDeps,
    scalaVersion := scala3Version
  )

lazy val scala_lang_4 =
  (project in file("scala-lang-modules/scala-lang-4")).settings(
    libraryDependencies ++= scalaTestDeps,
    scalaVersion := scala3Version
  )

lazy val cats_effects = (project in file("cats-effects"))
  .settings(
    name := "cats-effects",
    scalaVersion := scala3Version,
    libraryDependencies += catsEffect,
    libraryDependencies += "org.typelevel" %% "munit-cats-effect" % "2.0.0" % Test,
    libraryDependencies ++= scalaTestDeps,
    libraryDependencies += "junit" % "junit" % "4.13.2" % Test
  )

val zioJsonDep = "dev.zio" %% "zio-json" % "0.7.1"
val zioTestSbt = "dev.zio" %% "zio-test-sbt" % zioVersion % Test

lazy val zio = (project in file("zio"))
  .settings(
    name := "zio",
    scalaVersion := scala3Version,
    libraryDependencies += "dev.zio" %% "zio" % zioVersion,
    libraryDependencies += "dev.zio" %% "zio-streams" % zioVersion,
    libraryDependencies += zioTestSbt,
    libraryDependencies += "dev.zio" %% "zio-kafka" % "2.7.5",
    libraryDependencies += zioJsonDep,
    libraryDependencies += "dev.zio" %% "zio-prelude" % "1.0.0-RC23",
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.19" % Test,
    testFrameworks += new TestFramework("zio.test.sbt.ZTestFramework")
  )

lazy val zio2 = (project in file("zio-2"))
  .settings(
    name := "zio-2",
    scalaVersion := scala3Version,
    libraryDependencies += "dev.zio" %% "zio" % zioVersion,
    libraryDependencies += "dev.zio" %% "zio-json" % "0.7.1",
    libraryDependencies += "dev.zio" %% "zio-test" % zioVersion % Test,
    libraryDependencies += "dev.zio" %% "zio-test-sbt" % zioVersion % Test,
    libraryDependencies ++= scalaTestDeps,
    testFrameworks += new TestFramework("zio.test.sbt.ZTestFramework")
  )

lazy val zio3 = (project in file("zio3"))
  .settings(
    libraryDependencies ++= Seq(
      zioJsonDep,
      "dev.zio" %% "zio-http" % "3.0.0-RC6",
      "io.getquill" %% "quill-zio" % "4.8.4",
      "io.getquill" %% "quill-jdbc-zio" % "4.8.4",
      "com.h2database" % "h2" % "2.2.220"
    ),
    libraryDependencies ++= Seq(
      "dev.zio" %% "zio-test" % zioVersion % Test,
      zioTestSbt,
      "dev.zio" %% "zio-test-magnolia" % zioVersion % Test,
      "dev.zio" %% "zio-http-testkit" % "3.0.0-RC6" % Test
    ),
    testFrameworks += new TestFramework("zio.test.sbt.ZTestFramework"),
    run / fork := true
  )

val doobieVersion = "1.0.0-RC2"

val doobiePGDep = "org.tpolecat" %% "doobie-postgres" % doobieVersion
val doobieCore = "org.tpolecat" %% "doobie-core" % doobieVersion

// Scala Native Project is disabled as it needs clang to installed in the target machine.
// To test the scala-native code, install clang and then uncomment this build
// lazy val scala_native = (project in file("scala-native"))
//   .settings(
//     name := "scala-native",
//     scalaVersion := scala3Version,
//     libraryDependencies += "com.lihaoyi" %%% "fansi" % "0.4.0"
//   )

// ScalaPy Python Project is disabled as it needs clang and python to installed in the target machine.
// To test this code, install clang, python and then uncommment this build
// lazy val scala_python = (project in file("scala-python"))
//   .settings(
//     name := "scala-python",
//     libraryDependencies += "dev.scalapy" %%% "scalapy-core" % "0.5.3",
//     fork := true,
//     scalaVersion := scala3Version
//   )

lazy val reflection = (project in file("reflection"))
  .settings(
    name := "reflection",
    libraryDependencies += scalaReflection,
    libraryDependencies += "junit" % "junit" % "4.13.2" % Test
  )

Test / testOptions += Tests.Argument(TestFrameworks.ScalaTest, "-eG")

lazy val scala212 = (project in file("scala-2-modules/scala212"))
  .settings(
    scalaVersion := ScalaVersions.scala212Version,
    name := "scala212",
    libraryDependencies ++= scalaTestDeps
  )

lazy val spark_scala = (project in file("spark-scala"))
  .settings(
    libraryDependencies ++= Seq(
      sparkSqlDep,
      sparkCoreDep
    ) ++ scalaTestDeps,
    fork := true,
    javaOptions ++= Seq(
      "--add-exports=java.base/sun.nio.ch=ALL-UNNAMED" // Added for JDK 17 issue with Spark
    )
  )

// This is here to prevent the accidental addition of the stand-alone module for SCALA-156
// Do not uncomment the next line
// lazy val spark_scala = (project in file("sbt-standalone"))

addCommandAlias(
  "ci",
  ";compile;test:compile;it:compile;scalafmtCheckAll;validateUnitTestNames;test"
)

addCommandAlias(
  "integrationTests",
  """;set ThisBuild/IntegrationTest/testOptions += Tests.Filter(t => !t.endsWith("ManualTest") && !t.endsWith("LiveTest") ); it:test""".stripMargin
)

addCommandAlias(
  "ciFull",
  """;clean; ci; set ThisBuild/IntegrationTest/testOptions += Tests.Filter(t => !t.endsWith("ManualTest") && !t.endsWith("LiveTest") ); it:test""".stripMargin
)

addCommandAlias(
  "manualTests",
  """;ci; set ThisBuild/IntegrationTest/testOptions += Tests.Filter(t => t.endsWith("ManualTest")); it:test""".stripMargin
)

addCommandAlias(
  "liveTests",
  """;ci; set ThisBuild/IntegrationTest/testOptions += Tests.Filter(t => t.endsWith("LiveTest")); it:test""".stripMargin
)

lazy val playGroup = (project in file("play-scala"))

//Uncomment this to enable scala-js module. It needs nodejs module as well in local machine
//lazy val scalajs = (project in file("scala-js"))
//  .settings(
//    scalaVersion := scala3Version
//  )
lazy val scalatra = project in file("scalatra")
lazy val benchmark = project in file("specialized-benchmark")

ThisBuild / libraryDependencySchemes += "org.scala-lang.modules" %% "scala-xml" % VersionScheme.Always
