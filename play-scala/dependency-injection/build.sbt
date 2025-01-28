name := """play-dependency-injection"""

version := "1.0-SNAPSHOT"

enablePlugins(PlayScala)

scalaVersion := ScalaVersions.scala3Version

libraryDependencies += guice
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % LibraryVersions.scalatestPlayVersion % Test
libraryDependencies += "com.softwaremill.macwire" %% "macros" % "2.6.6" % Provided
libraryDependencies += "com.softwaremill.macwire" %% "util" % "2.6.6"
