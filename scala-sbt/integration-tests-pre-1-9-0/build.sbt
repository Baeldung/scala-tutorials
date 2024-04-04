name := "sbt-integration-tests-pre-1-9-0"
version := "1.0.0"

ThisBuild / scalaVersion := "3.2.0"

lazy val root = (project in file("."))
  .configs(IntegrationTest)
  .settings(
    Defaults.itSettings,
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.17" % "it"
  )
