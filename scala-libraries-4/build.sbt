val scalaTestDeps = Seq(
  "org.scalatest" %% "scalatest" % "3.2.16" % Test,
  "org.scalatest" %% "scalatest-shouldmatchers" % "3.2.16" % Test,
  "org.scalatest" %% "scalatest-wordspec" % "3.2.16" % Test,
  "org.scalatest" %% "scalatest-flatspec" % "3.2.16" % Test
)
val jUnitInterface = "com.github.sbt" % "junit-interface" % "0.13.3" % "test"
val scalaMock = "org.scalamock" %% "scalamock" % "5.2.0" % Test

val http4sBlaze = "0.23.15"
val http4sVersion = "0.23.23"
val sparkVersion = "3.4.1"
val osLibVersion = "0.9.1"
val scalaV = "2.13.11"
scalaVersion := scalaV
name := "scala-libraries-4"
val logbackDep = "ch.qos.logback" % "logback-classic" % "1.3.8"
val scalaReflection = "org.scala-lang" % "scala-reflect" % scalaV
libraryDependencies += "com.lihaoyi" %% "utest" % "0.8.1" % "test"
testFrameworks += new TestFramework("utest.runner.Framework")
val sparkCoreDep = "org.apache.spark" %% "spark-core" % sparkVersion
val sparkSqlDep = "org.apache.spark" %% "spark-sql" % sparkVersion
configs(IntegrationTest)
libraryDependencies ++= scalaTestDeps
  .map(_.withConfigurations(Some("it,test")))
libraryDependencies ++= Seq(
  "org.scala-lang.modules" %% "scala-async" % "1.0.1",
  scalaReflection % Provided,
  "org.tpolecat" %% "skunk-core" % "0.6.0",
  sparkSqlDep,
  sparkCoreDep,
  logbackDep,
  "com.typesafe.scala-logging" %% "scala-logging" % "3.9.5",
  "org.typelevel" %% "cats-core" % "2.9.0"
)
libraryDependencies ++= Seq(
  "com.clever-cloud.pulsar4s" %% "pulsar4s-core" % "2.9.0",
  "com.clever-cloud.pulsar4s" %% "pulsar4s-jackson" % "2.9.0",
  "org.testcontainers" % "pulsar" % "1.18.3" % IntegrationTest
)
libraryDependencies ++= Seq(
  "software.amazon.awssdk" % "s3" % "2.20.111",
  "com.amazonaws" % "aws-java-sdk-s3" % "1.12.501" % IntegrationTest,
  "com.dimafeng" %% "testcontainers-scala-scalatest" % "0.40.17" % IntegrationTest,
  "com.dimafeng" %% "testcontainers-scala-localstack-v2" % "0.40.17" % IntegrationTest
)
libraryDependencies ++= Seq(
  "com.github.seratch" %% "awscala" % "0.9.2"
)
scalacOptions += "-Xasync"
Defaults.itSettings
IntegrationTest / fork := true
ThisBuild / libraryDependencySchemes += "org.scala-lang.modules" %% "scala-xml" % VersionScheme.Always
