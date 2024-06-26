name := """configuration-access"""
organization := "com.baeldung"

version := "1.0-SNAPSHOT"

enablePlugins(PlayScala)

scalaVersion := ScalaVersions.scala3Version

libraryDependencies += guice
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % LibraryVersions.scalatestPlayVersion % Test
libraryDependencies += "org.scalatestplus" %% "mockito-3-4" % "3.2.10.0"

// Adds additional packages into Twirl
//TwirlKeys.templateImports += "com.baeldung.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "com.baeldung.binders._"
