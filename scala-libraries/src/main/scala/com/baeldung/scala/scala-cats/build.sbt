name := "scala-cats"

version := "0.1"

scalaVersion := "2.12.8"

libraryDependencies ++= Seq(
  "org.typelevel" %% "cats-core" % "2.2.0",
  "org.scalatest" %% "scalatest" % "3.2.2" % Test
)
