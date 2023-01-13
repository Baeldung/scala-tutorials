ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.8"
//
libraryDependencies += "org.scalatest" %% "scalatest-flatspec" % "3.2.11" % "test"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.1.2" % Test

// JVM
libraryDependencies += "me.shadaj" %% "scalapy-core" % "0.5.2"

// Scala Native
libraryDependencies += "me.shadaj" %% "scalapy-core" % "0.5.2"

// python-native-libs
libraryDependencies += "ai.kien" %% "python-native-libs" % "0.2.2"

lazy val root = (project in file("."))
  .settings(
    name := "SBTString"
  )

fork := true

import scala.sys.process._
lazy val pythonLdFlags = {
  val withoutEmbed = "python3-config --ldflags --embed".!!
  if (withoutEmbed.contains("-lpython")) {
    withoutEmbed.split(' ').map(_.trim).filter(_.nonEmpty).toSeq
  } else {
    val withEmbed = "python3-config --ldflags --embed".!!
    withEmbed.split(' ').map(_.trim).filter(_.nonEmpty).toSeq
  }
}

lazy val pythonLibsDir = {
  pythonLdFlags.find(_.startsWith("-L")).get.drop("-L".length)
}

javaOptions += s"-Djna.library.path=$pythonLibsDir"
