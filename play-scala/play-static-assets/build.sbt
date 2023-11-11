name := """play-static-assets"""
organization := "com.baeldung"

version := "1.0-SNAPSHOT"

enablePlugins(PlayScala)
enablePlugins(SbtWeb)

// scalaVersion := ScalaVersions.scala2Version
scalaVersion := "2.13.12"

libraryDependencies += guice
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "7.0.0" % Test
libraryDependencies += "org.webjars.bower" % "bootstrap-sass" % "3.3.7"

// See https://github.com/sbt/sbt-js-engine/issues/68
javaOptions ++= Seq("-Djdk.lang.Process.allowAmbiguousCommands=true")

// uncomment only when running "sbt run" in CLI.
// This fails in sbt scalafmt and isn't recognized by InteliJ
// Assets / LessKeys.less / includeFilter := "*.less"
