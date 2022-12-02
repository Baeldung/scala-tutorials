val scalaV = "2.12.15"
ThisBuild / scalaVersion := scalaV
ThisBuild / version := "1.0-SNAPSHOT"
ThisBuild / organization := "com.baeldung"
ThisBuild / organizationName := "core-scala"

val scalaTest = "org.scalatest" %% "scalatest" % "3.1.2" % Test
val junit = "com.novocode" % "junit-interface" % "0.11" % "test"
val catsEffect = "org.typelevel" % "cats-effect_2.12" % "2.1.4"
val catsCore = "org.typelevel" % "cats-effect_2.12" % "2.1.4"

lazy val scala_core = (project in file("scala-core"))
  .settings(
    name := "scala-core",
    libraryDependencies ++=
      Seq(
        scalaTest,
        junit,
        catsCore,
        catsEffect
      )
  )

lazy val scala_core_2 = (project in file("scala-core-2"))
  .settings(
    name := "scala-core-2",
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.5" % Test,
    libraryDependencies += "org.scalamock" %% "scalamock" % "4.4.0" % Test,
    libraryDependencies += "com.novocode" % "junit-interface" % "0.11" % "test"
  )

lazy val scala_core_3 = (project in file("scala-core-3"))
  .settings(
    name := "scala-core-3",
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.5" % Test,
    libraryDependencies += "com.novocode" % "junit-interface" % "0.11" % "test",
    libraryDependencies += "org.scala-lang" % "scala-reflect" % scalaV,
    libraryDependencies += "org.scala-lang.modules" %% "scala-xml" % "2.0.0-M1"
  )

lazy val scala_core_4 = (project in file("scala-core-4"))
  .settings(
    name := "scala-core-4",
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.5" % Test,
    libraryDependencies += "com.novocode" % "junit-interface" % "0.11" % "test",
    libraryDependencies += "org.scala-lang" % "scala-reflect" % scalaV,
    libraryDependencies += "org.scala-lang.modules" %% "scala-xml" % "2.0.0-M1"
  )

lazy val scala_core_5 = (project in file("scala-core-5"))
  .settings(
    name := "scala-core-5",
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.5" % Test,
    libraryDependencies += "com.novocode" % "junit-interface" % "0.11" % "test",
    libraryDependencies += "org.scala-lang" % "scala-reflect" % scalaV,
    libraryDependencies += "org.scala-lang.modules" %% "scala-xml" % "2.0.0-M1",
    libraryDependencies += "joda-time" % "joda-time" % "2.9.9",
    libraryDependencies += "org.joda" % "joda-convert" % "2.2.1",
    libraryDependencies += "com.github.nscala-time" %% "nscala-time" % "2.28.0"
  )

lazy val scala_core_6 = (project in file("scala-core-6"))
  .settings(
    name := "scala-core-6",
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.5" % Test,
    libraryDependencies += "org.scalamock" %% "scalamock" % "4.4.0" % Test,
    libraryDependencies += "com.novocode" % "junit-interface" % "0.11" % "test"
  )

lazy val scala_core_7 = (project in file("scala-core-7"))
  .settings(
    name := "scala-core-7",
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.5" % Test,
    libraryDependencies += "org.scalamock" %% "scalamock" % "4.4.0" % Test,
    libraryDependencies += "com.novocode" % "junit-interface" % "0.11" % "test",
    libraryDependencies += "com.github.scopt" %% "scopt" % "4.0.1",
    libraryDependencies += "org.rogach" %% "scallop" % "4.1.0",
    libraryDependencies += "org.backuity.clist" %% "clist-core" % "3.5.1",
    libraryDependencies += "org.backuity.clist" %% "clist-macros" % "3.5.1" % "provided",
    libraryDependencies += "args4j" % "args4j" % "2.33"
  )

lazy val scala_core_io = (project in file("scala-core-io"))
  .settings(
    name := "scala-core-io",
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.1.2" % Test,
    libraryDependencies += "com.novocode" % "junit-interface" % "0.11" % "test",
    libraryDependencies += "org.scala-lang" % "scala-compiler" % scalaV
  )

lazy val scala_core_oop = (project in file("scala-core-oop"))
  .settings(
    name := "scala-core-oop",
    libraryDependencies ++=
      Seq(catsCore, scalaTest, junit)
  )

lazy val scala_core_fp = (project in file("scala-core-fp"))
  .settings(
    name := "scala-core-fp",
    libraryDependencies ++=
      Seq(catsCore, scalaTest, junit)
  )

lazy val scala_lang = (project in file("scala-lang"))
  .settings(
    name := "scala-lang",
    scalacOptions += "-Ypartial-unification",
    libraryDependencies ++=
      Seq(scalaTest, junit)
  )

lazy val scala_lang_2 = (project in file("scala-lang-2"))
  .settings(
    name := "scala-lang",
    scalacOptions += "-Ypartial-unification",
    libraryDependencies ++=
      Seq(scalaTest, junit)
  )

lazy val scala_core_collections = (project in file("scala-core-collections"))
  .settings(
    name := "scala-core-collections",
    libraryDependencies +=
      scalaTest
  )

lazy val scala_test = (project in file("scala-test"))
  .settings(
    name := "scala-test",
    libraryDependencies ++=
      Seq(
        "org.scalatestplus" %% "mockito-3-4" % "3.2.10.0" % Test,
        "org.scalatest" %% "scalatest" % "3.2.11" % Test,
        junit,
        "org.scalamock" %% "scalamock" % "4.4.0" % Test
      )
  )

lazy val scala_akka_dependencies: Seq[ModuleID] = Seq(
  "com.typesafe.akka" % "akka-actor-typed_2.12" % "2.6.19",
  "ch.qos.logback" % "logback-classic" % "1.2.3",
  "com.typesafe.akka" % "akka-actor-testkit-typed_2.12" % "2.6.19" % Test,
  "com.lightbend.akka" %% "akka-stream-alpakka-mongodb" % "2.0.1",
  "com.typesafe.akka" %% "akka-stream" % "2.6.19",
  "org.mongodb.scala" %% "mongo-scala-driver" % "2.9.0",
  "com.lightbend.akka" %% "akka-stream-alpakka-file" % "2.0.2",
  "org.scalatest" %% "scalatest" % "3.0.5" % Test,
  "com.novocode" % "junit-interface" % "0.11" % "test",
  "de.flapdoodle.embed" % "de.flapdoodle.embed.mongo" % "2.2.0" % Test,
  "com.typesafe.akka" %% "akka-http" % "10.2.7"
)
lazy val scala_test_junit4 = (project in file("scala-test-junit4"))
  .settings(
    name := "scala-test-junit4",
    libraryDependencies ++=
      Seq(
        "org.scalatestplus" %% "junit-4-12" % "3.2.2.0" % Test,
        junit
      )
  )

lazy val scala_akka = (project in file("scala-akka"))
  .settings(
    name := "scala-akka",
    libraryDependencies ++= scala_akka_dependencies
  )

lazy val scala_akka_2 = (project in file("scala-akka-2"))
  .enablePlugins(AkkaGrpcPlugin)
  .settings(
    name := "scala-akka-2",
    libraryDependencies ++= scala_akka_dependencies,
    libraryDependencies += "com.lightbend.akka" %% "akka-stream-alpakka-sse" % "3.0.4",
    libraryDependencies += "com.typesafe.akka" %% "akka-stream-testkit" % "2.6.19",
    libraryDependencies += "com.typesafe.akka" %% "akka-http-testkit" % "10.2.7"
)
val monocleVersion = "2.0.4"
val slickVersion = "3.3.2"
val shapelessVersion = "2.3.3"
val scalazVersion = "7.3.2"
val fs2Version = "2.5-15-e328d68"
val AkkaVersion = "2.6.12"
val reactiveMongo = "1.0.3"

lazy val scala_libraries = (project in file("scala-libraries"))
  .settings(
    name := "scala-libraries",
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.1.2" % Test,
    libraryDependencies ++= Seq(
      "com.github.julien-truffaut" %% "monocle-core" % monocleVersion,
      "com.github.julien-truffaut" %% "monocle-macro" % monocleVersion,
      "com.github.julien-truffaut" %% "monocle-law" % monocleVersion % "test",
      "com.typesafe.slick" %% "slick" % slickVersion,
      "com.h2database" % "h2" % "1.4.200",
      "com.chuusai" %% "shapeless" % shapelessVersion,
      "com.h2database" % "h2" % "1.4.200",
      "org.scalaz" %% "scalaz-core" % scalazVersion,
      "co.fs2" %% "fs2-core" % fs2Version,
      "co.fs2" %% "fs2-io" % fs2Version,
      "junit" % "junit" % "4.13" % Test,
      "org.reactivemongo" %% "reactivemongo" % reactiveMongo,
      "org.reactivemongo" %% "reactivemongo-akkastream" % reactiveMongo,
      "de.flapdoodle.embed" % "de.flapdoodle.embed.mongo" % "3.0.0" % Test,
      "ch.qos.logback" % "logback-classic" % "1.2.3" % Test,
      "com.typesafe.akka" %% "akka-actor-typed" % AkkaVersion,
      "com.typesafe.akka" %% "akka-stream" % AkkaVersion,
      "com.typesafe.akka" %% "akka-protobuf" % AkkaVersion
    )
  )

val circeVersion = "0.14.1"
val monixVersion = "3.4.0"
val elastic4sVersion = "7.16.0"
val sparkVersion = "3.2.1"

lazy val scala_libraries_2 = (project in file("scala-libraries-2"))
  .settings(
    name := "scala-libraries",
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.1.2" % Test,
    libraryDependencies ++= Seq(
      "io.circe" %% "circe-core" % circeVersion,
      "io.circe" %% "circe-generic" % circeVersion,
      "io.circe" %% "circe-parser" % circeVersion,
      "com.github.cb372" %% "scalacache-core" % "0.28.0",
      "com.github.cb372" %% "scalacache-guava" % "0.28.0",
      "com.github.cb372" %% "scalacache-cats-effect" % "0.28.0",
      "com.github.cb372" %% "scalacache-caffeine" % "0.28.0",
      "com.beachape" %% "enumeratum" % "1.7.0"
    ),
    libraryDependencies ++= Seq(
      "com.typesafe.play" %% "play-slick" % "5.0.0",
      "org.postgresql" % "postgresql" % "42.2.12"
    ),
    libraryDependencies ++= Seq(
      "io.monix" %% "monix" % monixVersion
    ),
    libraryDependencies ++= Seq(
      "org.apache.spark" %% "spark-core" % sparkVersion,
      "org.apache.spark" %% "spark-sql" % sparkVersion
    ),
    dependencyOverrides := Seq(
      "com.typesafe.akka" %% "akka-protobuf-v3" % "2.6.16",
      "com.typesafe.akka" %% "akka-stream" % "2.6.16",
      "com.typesafe.akka" %% "akka-serialization-jackson" % "2.6.16"
    ),
    libraryDependencies ++= Seq(
      "com.typesafe.akka" %% "akka-actor-testkit-typed" % "2.6.16" % Test,
      "org.scalatest" %% "scalatest" % "3.1.4" % Test,
      "org.scalacheck" %% "scalacheck" % "1.14.1" % Test,
      "com.lihaoyi" %% "requests" % "0.6.9"
    ),
    libraryDependencies ++= Seq(
      "com.sksamuel.elastic4s" %% "elastic4s-client-esjava" % elastic4sVersion,
      "com.sksamuel.elastic4s" %% "elastic4s-core" % elastic4sVersion,
      "ch.qos.logback" % "logback-classic" % "1.2.3"
    )
  )

val http4sVersion = "0.23.10"

lazy val scala_libraries_3 = (project in file("scala-libraries-3"))
  .settings(
    name := "scala-libraries",
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.1.2" % Test,
    libraryDependencies ++= Seq(
      "org.apache.spark" %% "spark-core" % sparkVersion,
      "org.apache.spark" %% "spark-sql" % sparkVersion
    ),
    libraryDependencies ++= Seq(
      "org.http4s" %% "http4s-dsl" % http4sVersion,
      "org.http4s" %% "http4s-blaze-server" % http4sVersion,
      "org.http4s" %% "http4s-blaze-client" % http4sVersion,
      "com.beachape" %% "enumeratum" % "1.7.0",
      "com.github.pureconfig" %% "pureconfig" % "0.17.1",
      "com.github.pureconfig" %% "pureconfig-enumeratum" % "0.17.1",
      "com.typesafe" % "config" % "1.4.2",
      "org.scalameta" %% "munit" % "0.7.29" % Test
    ),
    libraryDependencies += "org.scalamock" %% "scalamock" % "5.1.0" % Test,
    libraryDependencies += "com.softwaremill.retry" %% "retry" % "0.3.5",
    libraryDependencies ++= Seq(
      "org.apache.logging.log4j" %% "log4j-api-scala" % "12.0",
      "org.apache.logging.log4j" % "log4j-core" % "2.13.0" % Runtime
    ),
    libraryDependencies += "com.lihaoyi" %% "os-lib" % "0.8.1"
  )

lazy val scala_libraries_4 = (project in file("scala-libraries-4"))
  .settings(
    name := "scala-libraries-4",
    libraryDependencies += "com.lihaoyi" %% "utest" % "0.8.1" % "test",
    testFrameworks += new TestFramework("utest.runner.Framework")
  )

lazy val scala_strings = (project in file("scala-strings"))
  .settings(
    name := "scala-strings",
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.5" % Test,
    libraryDependencies += "com.novocode" % "junit-interface" % "0.11" % "test"
  )

lazy val scala_design_patterns = (project in file("scala-design-patterns"))
  .settings(
    name := "scala-design-patterns",
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.5" % Test,
    libraryDependencies += "org.scalamock" %% "scalamock" % "4.4.0" % Test,
    libraryDependencies += "com.novocode" % "junit-interface" % "0.11" % Test
  )

lazy val scala3_lang = project in file("scala3-lang")

lazy val scala3_lang_2 = project in file("scala3-lang-2")

lazy val cats_effects = (project in file("cats-effects"))
  .settings(
    name := "cats-effects",
    libraryDependencies += "org.typelevel" %% "cats-effect" % "3.3.13",
    libraryDependencies += "org.typelevel" %% "munit-cats-effect-3" % "1.0.7" % Test,
    libraryDependencies += "junit" % "junit" % "4.13" % Test
  )

lazy val zio = (project in file("zio"))
  .settings(
    name := "zio",
    libraryDependencies += "dev.zio" %% "zio" % "2.0.3",
    libraryDependencies += "dev.zio" %% "zio-streams" % "2.0.3",
    libraryDependencies += "dev.zio" %% "zio-test-sbt" % "2.0.3" % "test",
    testFrameworks += new TestFramework("zio.test.sbt.ZTestFramework")
  )

lazy val doobie = (project in file("doobie"))
  .settings(
    name := "doobie",
    libraryDependencies += "org.tpolecat" %% "doobie-core" % "1.0.0-RC1",
    libraryDependencies += "org.tpolecat" %% "doobie-postgres" % "1.0.0-RC1"
  )

// Scala Native Project is disabled as it needs clang to installed in the target machine.
// To test the scala-native code, install clang and then uncommment this build
// lazy val scala_native = (project in file("scala-native"))
//   .settings(
//     name := "scala-native",
//     libraryDependencies += "com.lihaoyi" %%% "fansi" % "0.3.0"
//   )

lazy val reflection = (project in file("reflection"))
  .settings(
    name := "reflection",
    libraryDependencies += "org.scala-lang" % "scala-reflect" % scalaV,
    libraryDependencies += "junit" % "junit" % "4.13" % Test
  )

lazy val scala3_libraries = (project in file("scala3-libraries"))
  .settings(
    scalaVersion := "3.1.1",
    name := "scala3-libraries",
    libraryDependencies ++= Seq(
      "com.github.japgolly.clearconfig" %% "core" % "3.0.0",
      "org.scalameta" %% "munit" % "0.7.29" % Test
    )
  )

testOptions in Test += Tests.Argument(TestFrameworks.ScalaTest, "-eG")
