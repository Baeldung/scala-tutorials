name := "scala-python"

libraryDependencies += "me.shadaj" %% "scalapy-core" % "0.5.2"

fork := true

import ai.kien.python.Python
lazy val python = Python()


lazy val javaOpts = python.scalapyProperties.get.map {
  case (k, v) => s"""-D$k=$v"""
}.toSeq

javaOptions ++= javaOpts