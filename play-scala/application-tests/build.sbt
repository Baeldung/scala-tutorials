name := """application-tests"""
organization := "com.baeldung"

version := "1.0-SNAPSHOT"

enablePlugins(PlayScala)

scalaVersion := ScalaVersions.scala2Version

libraryDependencies += guice
libraryDependencies += "com.h2database" % "h2" % "1.4.200"
libraryDependencies += "org.postgresql" % "postgresql" % "42.2.27"
libraryDependencies += "com.typesafe.play" %% "play-slick" % "5.2.0-RC1"
libraryDependencies += "com.typesafe.play" %% "play-slick-evolutions" % "5.2.0-RC1"
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "6.0.0" % Test
libraryDependencies += "org.scalatestplus" %% "mockito-3-4" % "3.2.10.0" % Test
