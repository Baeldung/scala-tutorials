name := """configuration-access"""
organization := "com.baeldung"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.13.3"

libraryDependencies += guice
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "5.0.0" % Test
libraryDependencies += "org.mockito" % "mockito-scala_2.13" % "1.15.0" % Test

// Adds additional packages into Twirl
//TwirlKeys.templateImports += "com.baeldung.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "com.baeldung.binders._"
