val scalaTestDeps = Seq(
  "org.scalatest" %% "scalatest" % "3.2.16" % Test,
  "org.scalatest" %% "scalatest-shouldmatchers" % "3.2.16" % Test,
  "org.scalatest" %% "scalatest-wordspec" % "3.2.16" % Test,
  "org.scalatest" %% "scalatest-flatspec" % "3.2.16" % Test
)
val jUnitInterface = "com.github.sbt" % "junit-interface" % "0.13.3" % "test"
val scalaMock = "org.scalamock" %% "scalamock" % "5.2.0" % Test
val scalaV = "2.13.11"
scalaVersion := scalaV
name := "scala-libraries-3"
val http4sBlaze = "0.23.15"
val http4sVersion = "0.23.23"
val sparkVersion = "3.4.1"
val osLibVersion = "0.9.1"
libraryDependencies ++= scalaTestDeps
libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % sparkVersion,
  "org.apache.spark" %% "spark-sql" % sparkVersion
)
libraryDependencies ++= Seq(
  "org.http4s" %% "http4s-dsl" % http4sVersion,
  "org.http4s" %% "http4s-blaze-server" % http4sBlaze,
  "org.http4s" %% "http4s-blaze-client" % http4sBlaze,
  "com.beachape" %% "enumeratum" % "1.7.3",
  "com.github.pureconfig" %% "pureconfig" % "0.17.4",
  "com.github.pureconfig" %% "pureconfig-enumeratum" % "0.17.4",
  "com.typesafe" % "config" % "1.4.2",
  "org.scalameta" %% "munit" % "0.7.29" % Test
)
libraryDependencies += scalaMock
libraryDependencies += "com.softwaremill.retry" %% "retry" % "0.3.6"
libraryDependencies ++= Seq(
  "org.apache.logging.log4j" %% "log4j-api-scala" % "12.0",
  "org.apache.logging.log4j" % "log4j-core" % "2.20.0" % Runtime
)
libraryDependencies += "com.lihaoyi" %% "os-lib" % osLibVersion
