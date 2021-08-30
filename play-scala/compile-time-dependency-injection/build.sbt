name := """play-dependency-injection"""
organization := "com.baeldung"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.13.6"

libraryDependencies += guice
libraryDependencies += "com.softwaremill.macwire" %% "macros" % "2.4.0" % Provided
libraryDependencies += "com.softwaremill.macwire" %% "util" % "2.4.0"
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "5.0.0" % Test

