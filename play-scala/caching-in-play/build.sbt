name := """caching-in-play"""
organization := "com.baeldung"

version := "1.0-SNAPSHOT"

enablePlugins(PlayScala)

scalaVersion := ScalaVersions.scala2Version

libraryDependencies += guice
libraryDependencies += caffeine
libraryDependencies += ws
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "7.0.1" % Test
libraryDependencies += "org.mockito" % "mockito-core" % "5.10.0" % Test

PlayKeys.devSettings += "play.server.http.port" -> "9000"
