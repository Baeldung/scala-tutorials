val tapirV = "1.10.0"
ThisBuild / version := "1.0.0"
ThisBuild / organization := "com.baeldung"
scalaVersion := ScalaVersions.scala3Version

lazy val endpoint = (project in file("endpoint"))
  .settings(
    libraryDependencies += "com.softwaremill.sttp.tapir" %% "tapir-core" % tapirV
  )
  .settings(
    libraryDependencies += "com.softwaremill.sttp.tapir" %% "tapir-json-circe" % tapirV
  )

lazy val client = (project in file("client"))
  .dependsOn(endpoint)
  .settings(
    libraryDependencies += "com.softwaremill.sttp.tapir" %% "tapir-sttp-client" % tapirV
  )

lazy val server = (project in file("server"))
  .dependsOn(endpoint)
  .settings(
    libraryDependencies += "com.softwaremill.sttp.tapir" %% "tapir-akka-http-server" % tapirV
  )
  .settings(
    libraryDependencies += "com.softwaremill.sttp.tapir" %% "tapir-swagger-ui-bundle" % tapirV
  )
