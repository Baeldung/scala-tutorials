name := """play-dependency-injection"""

version := "1.0-SNAPSHOT"

enablePlugins(PlayScala)

scalaVersion := ScalaVersions.scala2Version

libraryDependencies += guice
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "5.0.0" % Test
libraryDependencies += "com.softwaremill.macwire" %% "macros" % "2.5.9" % Provided
libraryDependencies += "com.softwaremill.macwire" %% "util" % "2.5.9"
