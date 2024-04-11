name := "sbt-integration-tests-post-1-9-0"
version := "1.0.0"

ThisBuild / scalaVersion := "3.2.0"

lazy val root = (project in file("."))

lazy val integration = (project in file("integration"))
  .dependsOn(root)
  .settings(
    publish / skip := true,
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.17" % Test
  )
