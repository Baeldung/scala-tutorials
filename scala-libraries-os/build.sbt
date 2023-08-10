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
name := "scala-libraries-os"
val osLibVersion = "0.9.1"

name := "scala-libraries"
libraryDependencies ++= scalaTestDeps
libraryDependencies ++= Seq(
  "org.apache.logging.log4j" %% "log4j-api-scala" % "12.0",
  "org.apache.logging.log4j" % "log4j-core" % "2.20.0" % Runtime
)
libraryDependencies += "com.lihaoyi" %% "os-lib" % osLibVersion