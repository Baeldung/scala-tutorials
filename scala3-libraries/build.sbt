val scala3Version = "3.2.2"

scalaVersion := scala3Version

name := "scala3-libraries"
libraryDependencies ++= Seq(
  "com.github.japgolly.clearconfig" %% "core" % "3.1.0",
  "org.scalameta" %% "munit" % "0.7.29" % Test
)
