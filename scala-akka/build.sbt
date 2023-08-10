val scalaTestDeps = Seq(
  "org.scalatest" %% "scalatest" % "3.2.16" % Test,
  "org.scalatest" %% "scalatest-shouldmatchers" % "3.2.16" % Test,
  "org.scalatest" %% "scalatest-wordspec" % "3.2.16" % Test,
  "org.scalatest" %% "scalatest-flatspec" % "3.2.16" % Test
)
val AkkaVersion = "2.8.0"
val scalaV = "2.13.11"
scalaVersion := scalaV
val AkkaHttpVersion = "10.5.0"
val jUnitInterface = "com.github.sbt" % "junit-interface" % "0.13.3" % "test"
val embeddedMongo =
  "de.flapdoodle.embed" % "de.flapdoodle.embed.mongo" % "4.7.1"
lazy val scala_akka_dependencies: Seq[ModuleID] = Seq(
  "com.typesafe.akka" %% "akka-actor-typed" % AkkaVersion,
  "com.typesafe.akka" %% "akka-actor-testkit-typed" % AkkaVersion % Test,
  "com.lightbend.akka" %% "akka-stream-alpakka-mongodb" % "5.0.0",
  "com.typesafe.akka" %% "akka-stream" % AkkaVersion,
  "org.mongodb.scala" %% "mongo-scala-driver" % "4.10.2",
  "com.lightbend.akka" %% "akka-stream-alpakka-file" % "5.0.0",
  jUnitInterface,
  embeddedMongo % Test,
  "com.typesafe.akka" %% "akka-http" % AkkaHttpVersion
) ++ scalaTestDeps
configs(IntegrationTest)
name := "scala-akka"
libraryDependencies ++= scala_akka_dependencies ++ Seq(
  "ch.qos.logback" % "logback-classic" % "1.2.3", // scala-steward:off
  embeddedMongo % "it,compile"
) ++ scalaTestDeps.map(_.withConfigurations(Some("it,test")))

Defaults.itSettings
