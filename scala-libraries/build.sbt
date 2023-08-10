val scalaTestDeps = Seq(
  "org.scalatest" %% "scalatest" % "3.2.16" % Test,
  "org.scalatest" %% "scalatest-shouldmatchers" % "3.2.16" % Test,
  "org.scalatest" %% "scalatest-wordspec" % "3.2.16" % Test,
  "org.scalatest" %% "scalatest-flatspec" % "3.2.16" % Test
)
val jUnitInterface = "com.github.sbt" % "junit-interface" % "0.13.3" % "test"

name := "scala-lang-2"
libraryDependencies ++=
  Seq(jUnitInterface) ++ scalaTestDeps

val scalaV = "2.13.11"
scalaVersion := scalaV
val AkkaVersion = "2.8.0"
val monocleVersion = "2.1.0"
val slickVersion = "3.4.1"
val shapelessVersion = "2.3.10"
val scalazVersion = "7.3.7"
val fs2Version = "3.7.0"
val AkkaHttpVersion = "10.5.0"
val reactiveMongo = "1.0.10"
val embedMongoVersion = "4.7.1"
val catEffectTest = "org.typelevel" %% "cats-effect-testkit" % "3.5.1" % Test
val logbackDep = "ch.qos.logback" % "logback-classic" % "1.3.8"
name := "scala-libraries"
libraryDependencies ++= scalaTestDeps
libraryDependencies ++= Seq(
  "com.github.julien-truffaut" %% "monocle-core" % monocleVersion,
  "com.github.julien-truffaut" %% "monocle-macro" % monocleVersion,
  "com.github.julien-truffaut" %% "monocle-law" % monocleVersion % "test",
  "com.typesafe.slick" %% "slick" % slickVersion,
  "com.h2database" % "h2" % "2.2.220",
  "com.chuusai" %% "shapeless" % shapelessVersion,
  "org.scalaz" %% "scalaz-core" % scalazVersion,
  "co.fs2" %% "fs2-core" % fs2Version,
  "co.fs2" %% "fs2-io" % fs2Version,
  "junit" % "junit" % "4.13.2" % Test,
  "org.reactivemongo" %% "reactivemongo" % reactiveMongo,
  "org.reactivemongo" %% "reactivemongo-akkastream" % reactiveMongo,
  "de.flapdoodle.embed" % "de.flapdoodle.embed.mongo" % embedMongoVersion % Test,
  logbackDep % Test,
  "com.typesafe.akka" %% "akka-actor-typed" % AkkaVersion,
  "com.typesafe.akka" %% "akka-stream" % AkkaVersion,
  catEffectTest,
  "org.typelevel" %% "cats-effect-testing-scalatest" % "1.5.0" % Test
)

val circeVersion = "0.14.5"
val monixVersion = "3.4.1"
val elastic4sVersion = "8.8.1"
val sparkVersion = "3.4.1"

val sparkCoreDep = "org.apache.spark" %% "spark-core" % sparkVersion
val sparkSqlDep = "org.apache.spark" %% "spark-sql" % sparkVersion
