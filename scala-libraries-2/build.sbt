val scalaTestDeps = Seq(
  "org.scalatest" %% "scalatest" % "3.2.16" % Test,
  "org.scalatest" %% "scalatest-shouldmatchers" % "3.2.16" % Test,
  "org.scalatest" %% "scalatest-wordspec" % "3.2.16" % Test,
  "org.scalatest" %% "scalatest-flatspec" % "3.2.16" % Test
)
val jUnitInterface = "com.github.sbt" % "junit-interface" % "0.13.3" % "test"

name := "scala-libraries-2"
val scalaV = "2.13.11"
scalaVersion := scalaV
val AkkaVersion = "2.8.0"
val circeVersion = "0.14.5"
val monixVersion = "3.4.1"
val elastic4sVersion = "8.8.1"
val sparkVersion = "3.4.1"

libraryDependencies ++= scalaTestDeps
  .map(_.withConfigurations(Some("it,test")))

libraryDependencies ++= Seq(
  "io.circe" %% "circe-core" % circeVersion,
  "io.circe" %% "circe-generic" % circeVersion,
  "io.circe" %% "circe-parser" % circeVersion,
  "com.github.cb372" %% "scalacache-core" % "0.28.0",
  "com.github.cb372" %% "scalacache-guava" % "0.28.0",
  "com.github.cb372" %% "scalacache-cats-effect" % "0.28.0",
  "com.github.cb372" %% "scalacache-caffeine" % "0.28.0",
  "com.beachape" %% "enumeratum" % "1.7.3"
)
configs(IntegrationTest)
libraryDependencies ++= Seq(
  "com.typesafe.play" %% "play-slick" % "5.1.0",
  "org.postgresql" % "postgresql" % "42.6.0"
)

libraryDependencies ++= Seq(
  "io.monix" %% "monix" % monixVersion
)

dependencyOverrides := Seq(
  "com.typesafe.akka" %% "akka-protobuf-v3" % AkkaVersion,
  "com.typesafe.akka" %% "akka-stream" % AkkaVersion,
  "com.typesafe.akka" %% "akka-serialization-jackson" % AkkaVersion
)

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor-testkit-typed" % AkkaVersion % Test,
  "org.scalacheck" %% "scalacheck" % "1.17.0" % Test,
  "com.lihaoyi" %% "requests" % "0.8.0"
)

libraryDependencies ++= Seq(
  "com.sksamuel.elastic4s" %% "elastic4s-client-esjava" % elastic4sVersion,
  "com.sksamuel.elastic4s" %% "elastic4s-core" % elastic4sVersion,
  logback
)

Defaults.itSettings
