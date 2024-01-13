val scalaV = ScalaVersions.scala2Version
val scala3Version = ScalaVersions.scala3Version
ThisBuild / scalaVersion := scalaV
ThisBuild / version := "1.0-SNAPSHOT"
ThisBuild / organization := "com.baeldung"
ThisBuild / organizationName := "core-scala"

val jUnitInterface = "com.github.sbt" % "junit-interface" % "0.13.3" % "test"
val catsEffect = "org.typelevel" %% "cats-effect" % "3.5.2"
val catEffectTest = "org.typelevel" %% "cats-effect-testkit" % "3.5.2" % Test
val scalaReflection = "org.scala-lang" % "scala-reflect" % scalaV
val logback = "ch.qos.logback" % "logback-classic" % "1.4.14"
val embedMongoVersion = "4.12.0"

val scalaTestDeps = Seq(
  "org.scalatest" %% "scalatest" % "3.2.17" % Test,
  "org.scalatest" %% "scalatest-shouldmatchers" % "3.2.17" % Test,
  "org.scalatest" %% "scalatest-wordspec" % "3.2.17" % Test,
  "org.scalatest" %% "scalatest-flatspec" % "3.2.17" % Test
)

val scalaMock = "org.scalamock" %% "scalamock" % "5.2.0" % Test
val zioVersion = "2.0.21"

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

lazy val scala_core_3 = (project in file("scala-core-modules/scala-core-3"))
  .settings(
    name := "scala-core-3",
    scalaVersion := scala3Version,
    libraryDependencies ++= scalaTestDeps,
    libraryDependencies += jUnitInterface,
    libraryDependencies += "org.scala-lang.modules" %% "scala-xml" % "2.2.0"
  )

lazy val scala2_core = (project in file("scala-core-modules/scala2-core"))
  .settings(
    name := "scala2-core",
    libraryDependencies ++= scalaTestDeps,
    libraryDependencies += jUnitInterface,
    libraryDependencies += scalaReflection,
    libraryDependencies += "org.scala-lang.modules" %% "scala-xml" % "2.2.0",
    libraryDependencies += "com.github.scopt" %% "scopt" % "4.1.0",
    libraryDependencies += "org.rogach" %% "scallop" % "5.0.1",
    libraryDependencies += "org.backuity.clist" %% "clist-core" % "3.5.1",
    libraryDependencies += "org.backuity.clist" %% "clist-macros" % "3.5.1" % "provided",
    libraryDependencies += "args4j" % "args4j" % "2.33",
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
      libraryDependencies += "joda-time" % "joda-time" % "2.12.5",
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

lazy val scala_core_collections =
  (project in file("scala-core-collections-modules/scala-core-collections"))
    .settings(
      name := "scala-core-collections",
      scalaVersion := scala3Version,
      libraryDependencies ++= Seq(
        "org.scala-lang.modules" %% "scala-parallel-collections" % "1.0.4"
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
        "org.scalatestplus" %% "mockito-3-4" % "3.2.10.0" % Test,
        jUnitInterface
      ) ++ scalaTestDeps
  )

val embeddedMongo =
  "de.flapdoodle.embed" % "de.flapdoodle.embed.mongo" % embedMongoVersion

lazy val scala_akka_dependencies: Seq[ModuleID] = Seq(
  "com.typesafe.akka" %% "akka-actor-typed" % AkkaVersion,
  "com.typesafe.akka" %% "akka-actor-testkit-typed" % AkkaVersion % Test,
  "com.lightbend.akka" %% "akka-stream-alpakka-mongodb" % "5.0.0",
  "com.typesafe.akka" %% "akka-stream" % AkkaVersion,
  "org.mongodb.scala" %% "mongo-scala-driver" % "4.11.1",
  "com.lightbend.akka" %% "akka-stream-alpakka-file" % "5.0.0",
  jUnitInterface,
  embeddedMongo % Test,
  "com.typesafe.akka" %% "akka-http" % AkkaHttpVersion
) ++ scalaTestDeps

lazy val scala_test_junit4 = (project in file("scala-test-junit4"))
  .settings(
    name := "scala-test-junit4",
    scalaVersion := scala3Version,
    libraryDependencies ++=
      Seq(
        "org.scalatestplus" %% "junit-4-13" % "3.2.17.0" % Test,
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
      "com.typesafe.akka" %% "akka-actor-typed" % AkkaVersion,
      "com.typesafe.akka" %% "akka-stream" % AkkaVersion,
      "com.typesafe.akka" %% "akka-http" % AkkaHttpVersion,
      "com.typesafe.akka" %% "akka-discovery" % AkkaVersion,
      "com.typesafe.akka" %% "akka-http-spray-json" % AkkaHttpVersion,
      "com.typesafe.akka" %% "akka-http-testkit" % AkkaHttpVersion,
      "com.lightbend.akka" %% "akka-stream-alpakka-csv" % "5.0.0",
      "com.lightbend.akka" %% "akka-stream-alpakka-sse" % "5.0.0",
      "com.typesafe.akka" %% "akka-persistence-typed" % AkkaVersion,
      "com.typesafe.akka" %% "akka-actor-testkit-typed" % AkkaVersion % "it,test",
      "com.typesafe.akka" %% "akka-http-testkit" % AkkaHttpVersion % "it,test",
      "com.typesafe.akka" %% "akka-stream-testkit" % AkkaVersion % Test
    ) ++ scalaTestDeps.map(_.withConfigurations(Some("it,test")))
  )
val monocleVersion = "2.1.0"
val slickVersion = "3.4.1"
val shapelessVersion = "2.3.10"
val scalazVersion = "7.3.8"
val fs2Version = "3.9.3"
val AkkaVersion = "2.8.0"
val AkkaHttpVersion = "10.5.0"
val reactiveMongo = "1.0.10"

lazy val scala_libraries = (project in file("scala-libraries"))
  .settings(
    name := "scala-libraries",
    libraryDependencies ++= scalaTestDeps,
    libraryDependencies ++= Seq(
      "com.github.julien-truffaut" %% "monocle-core" % monocleVersion,
      "com.github.julien-truffaut" %% "monocle-macro" % monocleVersion,
      "com.github.julien-truffaut" %% "monocle-law" % monocleVersion % "test",
      "com.typesafe.slick" %% "slick" % slickVersion,
      "com.h2database" % "h2" % "2.2.224",
      "com.chuusai" %% "shapeless" % shapelessVersion,
      "org.scalaz" %% "scalaz-core" % scalazVersion,
      "co.fs2" %% "fs2-core" % fs2Version,
      "co.fs2" %% "fs2-io" % fs2Version,
      "junit" % "junit" % "4.13.2" % Test,
      "org.reactivemongo" %% "reactivemongo" % reactiveMongo,
      "org.reactivemongo" %% "reactivemongo-akkastream" % reactiveMongo,
      "de.flapdoodle.embed" % "de.flapdoodle.embed.mongo" % embedMongoVersion % Test,
      logback % Test,
      "com.typesafe.akka" %% "akka-actor-typed" % AkkaVersion,
      "com.typesafe.akka" %% "akka-stream" % AkkaVersion,
      catEffectTest,
      "org.typelevel" %% "cats-effect-testing-scalatest" % "1.5.0" % Test
    )
  )

val circeVersion = "0.14.6"
val monixVersion = "3.4.1"
val elastic4sVersion = "8.11.5"
val sparkVersion = "3.5.0"

val sparkCoreDep = "org.apache.spark" %% "spark-core" % sparkVersion
val sparkSqlDep = "org.apache.spark" %% "spark-sql" % sparkVersion

lazy val scala_libraries_2 = (project in file("scala-libraries-2"))
  .configs(IntegrationTest)
  .settings(
    name := "scala-libraries",
    libraryDependencies ++= scalaTestDeps
      .map(_.withConfigurations(Some("it,test"))),
    libraryDependencies ++= Seq(
      "io.circe" %% "circe-core" % circeVersion,
      "io.circe" %% "circe-generic" % circeVersion,
      "io.circe" %% "circe-parser" % circeVersion,
      "com.github.cb372" %% "scalacache-core" % "0.28.0",
      "com.github.cb372" %% "scalacache-guava" % "0.28.0",
      "com.github.cb372" %% "scalacache-cats-effect" % "0.28.0",
      "com.github.cb372" %% "scalacache-caffeine" % "0.28.0",
      "com.beachape" %% "enumeratum" % "1.7.3"
    ),
    libraryDependencies ++= Seq(
      "org.playframework" %% "play-slick" % "6.0.0",
      "org.postgresql" % "postgresql" % "42.7.1"
    ),
    libraryDependencies ++= Seq(
      "io.monix" %% "monix" % monixVersion
    ),
    dependencyOverrides := Seq(
      "com.typesafe.akka" %% "akka-protobuf-v3" % AkkaVersion,
      "com.typesafe.akka" %% "akka-stream" % AkkaVersion,
      "com.typesafe.akka" %% "akka-serialization-jackson" % AkkaVersion
    ),
    libraryDependencies ++= Seq(
      "com.typesafe.akka" %% "akka-actor-testkit-typed" % AkkaVersion % Test,
      "org.scalacheck" %% "scalacheck" % "1.17.0" % Test,
      "com.lihaoyi" %% "requests" % "0.8.0"
    ),
    libraryDependencies ++= Seq(
      "com.sksamuel.elastic4s" %% "elastic4s-client-esjava" % elastic4sVersion,
      "com.sksamuel.elastic4s" %% "elastic4s-core" % elastic4sVersion,
      logback
    ),
    Defaults.itSettings
  )

val http4sBlaze = "0.23.16"
val http4sVersion = "0.23.25"
val osLibVersion = "0.9.3"

lazy val scala_libraries_3 = (project in file("scala-libraries-3"))
  .settings(
    name := "scala-libraries",
    libraryDependencies ++= scalaTestDeps,
    libraryDependencies ++= Seq(
      "org.http4s" %% "http4s-dsl" % http4sVersion,
      "org.http4s" %% "http4s-blaze-server" % http4sBlaze,
      "org.http4s" %% "http4s-blaze-client" % http4sBlaze,
      "com.beachape" %% "enumeratum" % "1.7.3",
      "com.github.pureconfig" %% "pureconfig" % "0.17.4",
      "com.github.pureconfig" %% "pureconfig-enumeratum" % "0.17.4",
      "com.typesafe" % "config" % "1.4.3",
      "org.scalameta" %% "munit" % "0.7.29" % Test
    ),
    libraryDependencies += scalaMock,
    libraryDependencies += "com.softwaremill.retry" %% "retry" % "0.3.6",
    libraryDependencies ++= Seq(
      "org.apache.logging.log4j" %% "log4j-api-scala" % "13.0.0",
      "org.apache.logging.log4j" % "log4j-core" % "2.22.1" % Runtime
    ),
    libraryDependencies += "com.lihaoyi" %% "os-lib" % osLibVersion
  )

lazy val scala_libraries_os = (project in file("scala-libraries-os"))
  .settings(
    name := "scala-libraries",
    scalaVersion := scala3Version,
    libraryDependencies ++= scalaTestDeps,
    libraryDependencies ++= Seq(
      "org.apache.logging.log4j" %% "log4j-api-scala" % "13.0.0",
      "org.apache.logging.log4j" % "log4j-core" % "2.22.1" % Runtime
    ),
    libraryDependencies += "com.lihaoyi" %% "os-lib" % osLibVersion
  )

lazy val scala_libraries_4 = (project in file("scala-libraries-4"))
  .configs(IntegrationTest)
  .settings(
    name := "scala-libraries-4",
    scalaVersion := scalaV,
    libraryDependencies += "com.lihaoyi" %% "utest" % "0.8.2" % "test",
    testFrameworks += new TestFramework("utest.runner.Framework"),
    libraryDependencies ++= scalaTestDeps
      .map(_.withConfigurations(Some("it,test"))),
    libraryDependencies ++= Seq(
      "org.scala-lang.modules" %% "scala-async" % "1.0.1",
      scalaReflection % Provided,
      "org.tpolecat" %% "skunk-core" % "0.6.2",
      logback,
      "com.typesafe.scala-logging" %% "scala-logging" % "3.9.5",
      "org.typelevel" %% "cats-core" % "2.10.0"
    ),
    libraryDependencies ++= Seq(
      "com.clever-cloud.pulsar4s" %% "pulsar4s-core" % "2.9.0",
      "com.clever-cloud.pulsar4s" %% "pulsar4s-jackson" % "2.9.0",
      "org.testcontainers" % "pulsar" % "1.19.3" % IntegrationTest
    ),
    libraryDependencies ++= Seq(
      "software.amazon.awssdk" % "s3" % "2.22.13"
    ),
    libraryDependencies ++= Seq(
      "com.amazonaws" % "aws-java-sdk-s3" % "1.12.631" % IntegrationTest,
      "com.dimafeng" %% "testcontainers-scala-scalatest" % "0.41.0" % IntegrationTest,
      "com.dimafeng" %% "testcontainers-scala-localstack-v2" % "0.41.0" % IntegrationTest
    ),
    libraryDependencies ++= Seq(
      "com.github.seratch" %% "awscala" % "0.9.2"
    ),
    scalacOptions += "-Xasync",
    Defaults.itSettings,
    IntegrationTest / fork := true
  )

val spireVersion = "0.18.0"
val kafkaVersion = "7.5.3-ce"
val pureconfigVersion = "0.17.4"
val jackSonVersion = "2.16.1"
val log4jApiScalaVersion = "13.0.0"
val log4jVersion = "2.20.0"
val avro4sVersion = "4.1.1"
val kafkaAvroSerializer = "7.5.3"

lazy val scala_libraries_5 = (project in file("scala-libraries-5"))
  .settings(
    name := "scala-libraries-5",
    resolvers += "Kafka avro serializer" at "https://packages.confluent.io/maven",
    scalaVersion := scalaV,
    libraryDependencies ++= scalaTestDeps,
    libraryDependencies ++= Seq(
      "org.typelevel" %% "spire" % spireVersion,
      "org.apache.kafka" % "kafka-clients" % kafkaVersion,
      "com.github.pureconfig" %% "pureconfig" % pureconfigVersion,
      "com.fasterxml.jackson.core" % "jackson-databind" % jackSonVersion,
      "com.fasterxml.jackson.datatype" % "jackson-datatype-jsr310" % jackSonVersion,
      "com.fasterxml.jackson.module" %% "jackson-module-scala" % jackSonVersion,
      "com.sksamuel.avro4s" %% "avro4s-core" % avro4sVersion,
      "io.confluent" % "kafka-avro-serializer" % kafkaAvroSerializer,
      "org.apache.logging.log4j" %% "log4j-api-scala" % log4jApiScalaVersion,
      "org.apache.logging.log4j" % "log4j-core" % log4jVersion % Runtime
    )
  )

lazy val scala_libraries_6 = (project in file("scala-libraries-6"))
  .settings(
    name := "scala-libraries-6",
    scalaVersion := scala3Version,
    libraryDependencies ++= scalaTestDeps,
    libraryDependencies ++= Seq(
      "io.circe" %% "circe-yaml" % "1.15.0",
      "io.circe" %% "circe-generic" % "0.14.6",
      "io.circe" %% "circe-parser" % "0.14.6"
    )
  )

lazy val scala_strings = (project in file("scala-strings"))
  .settings(
    name := "scala-strings",
    scalaVersion := scala3Version,
    libraryDependencies ++= scalaTestDeps,
    libraryDependencies += jUnitInterface,
    libraryDependencies += "org.scala-lang.modules" %% "scala-parallel-collections" % "1.0.4",
    libraryDependencies += "org.scalatestplus" %% "scalacheck-1-17" % "3.2.17.0" % Test
  )

lazy val scala_design_patterns = (project in file("scala-design-patterns"))
  .settings(
    name := "scala-design-patterns",
    scalaVersion := scala3Version,
    libraryDependencies ++= scalaTestDeps,
    libraryDependencies += "org.scalatestplus" %% "mockito-3-4" % "3.2.10.0" % Test,
    libraryDependencies += "com.novocode" % "junit-interface" % "0.11" % Test
  )

lazy val scala3_lang =
  (project in file("scala-lang-modules/scala3-lang")).settings(
    libraryDependencies ++= scalaTestDeps,
    scalaVersion := scala3Version
  )

lazy val scala3_lang_2 =
  (project in file("scala-lang-modules/scala3-lang-2")).settings(
    libraryDependencies ++= scalaTestDeps,
    scalaVersion := scala3Version
  )

lazy val scala3_lang_3 =
  (project in file("scala-lang-modules/scala3-lang-3")).settings(
    libraryDependencies ++= scalaTestDeps,
    scalaVersion := scala3Version
  )

lazy val scala3_lang_collections =
  (project in file("scala3-lang-collections")).settings(
    libraryDependencies ++= scalaTestDeps,
    scalaVersion := scala3Version
  )

lazy val cats_effects = (project in file("cats-effects"))
  .settings(
    name := "cats-effects",
    scalaVersion := scala3Version,
    libraryDependencies += catsEffect,
    libraryDependencies += "org.typelevel" %% "munit-cats-effect-3" % "1.0.7" % Test,
    libraryDependencies ++= scalaTestDeps,
    libraryDependencies += "junit" % "junit" % "4.13.2" % Test
  )

lazy val zio = (project in file("zio"))
  .settings(
    name := "zio",
    scalaVersion := scala3Version,
    libraryDependencies += "dev.zio" %% "zio" % zioVersion,
    libraryDependencies += "dev.zio" %% "zio-streams" % zioVersion,
    libraryDependencies += "dev.zio" %% "zio-test-sbt" % zioVersion % Test,
    libraryDependencies ++= Seq(
      "dev.zio" %% "zio-json" % "0.6.2",
      "dev.zio" %% "zio-http" % "3.0.0-RC2",
      "io.getquill" %% "quill-zio" % "4.6.0",
      "io.getquill" %% "quill-jdbc-zio" % "4.6.0",
      "com.h2database" % "h2" % "2.2.220"
    ),
    libraryDependencies ++= Seq(
      "dev.zio" %% "zio-test" % zioVersion % Test,
      "dev.zio" %% "zio-test-magnolia" % zioVersion % Test,
      "dev.zio" %% "zio-http-testkit" % "3.0.0-RC2" % Test
    ),
    testFrameworks += new TestFramework("zio.test.sbt.ZTestFramework")
  )

lazy val doobie = (project in file("doobie"))
  .settings(
    name := "doobie",
    scalaVersion := scala3Version,
    libraryDependencies += "org.tpolecat" %% "doobie-core" % "1.0.0-RC2",
    libraryDependencies += "org.tpolecat" %% "doobie-postgres" % "1.0.0-RC2"
  )

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

lazy val scala3_libraries = (project in file("scala3-libraries"))
  .settings(
    scalaVersion := scala3Version,
    name := "scala3-libraries",
    libraryDependencies ++= Seq(
      "com.github.japgolly.clearconfig" %% "core" % "3.1.0",
      "org.scalameta" %% "munit" % "0.7.29" % Test,
      "org.tpolecat" %% "doobie-core" % "1.0.0-RC2",
      "org.tpolecat" %% "doobie-postgres" % "1.0.0-RC2"
    )
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

addCommandAlias(
  "ci",
  ";compile;test:compile;it:compile;scalafmtCheckAll;test"
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
