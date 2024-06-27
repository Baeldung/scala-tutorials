name := """application-tests"""
organization := "com.baeldung"

version := "1.0-SNAPSHOT"

enablePlugins(PlayScala)

scalaVersion := ScalaVersions.scala3Version

libraryDependencies += guice
libraryDependencies += "com.h2database" % "h2" % "1.4.200"
libraryDependencies += "org.postgresql" % "postgresql" % "42.2.29"
libraryDependencies += "org.playframework" %% "play-slick" % LibraryVersions.playSlickVersion
libraryDependencies += "org.playframework" %% "play-slick-evolutions" % LibraryVersions.playSlickVersion
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % LibraryVersions.scalatestPlayVersion % Test
libraryDependencies += "org.scalatestplus" %% "mockito-3-4" % "3.2.10.0" % Test
