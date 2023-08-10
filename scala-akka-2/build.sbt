val scalaTestDeps = Seq(
  "org.scalatest" %% "scalatest" % "3.2.16" % Test,
  "org.scalatest" %% "scalatest-shouldmatchers" % "3.2.16" % Test,
  "org.scalatest" %% "scalatest-wordspec" % "3.2.16" % Test,
  "org.scalatest" %% "scalatest-flatspec" % "3.2.16" % Test
)
val AkkaVersion = "2.8.0"
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
enablePlugins(AkkaGrpcPlugin)
configs(IntegrationTest)
val scalaV = "2.13.11"
scalaVersion := scalaV
name := "scala-akka-2"

Defaults.itSettings

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor-typed" % AkkaVersion,
  "com.typesafe.akka" %% "akka-stream" % AkkaVersion,
  "com.typesafe.akka" %% "akka-http" % AkkaHttpVersion,
  "com.typesafe.akka" %% "akka-discovery" % AkkaVersion,
  "com.typesafe.akka" %% "akka-http-spray-json" % AkkaHttpVersion,
  "com.typesafe.akka" %% "akka-http-testkit" % AkkaHttpVersion,
  "com.lightbend.akka" %% "akka-stream-alpakka-sse" % "5.0.0",
  "com.typesafe.akka" %% "akka-persistence-typed" % AkkaVersion,
  "com.typesafe.akka" %% "akka-actor-testkit-typed" % AkkaVersion % "it,test",
  "com.typesafe.akka" %% "akka-http-testkit" % AkkaHttpVersion % "it,test"
) ++ scalaTestDeps.map(_.withConfigurations(Some("it,test")))
