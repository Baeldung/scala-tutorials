val scalaTestDeps = Seq(
  "org.scalatest" %% "scalatest" % "3.2.16" % Test,
  "org.scalatest" %% "scalatest-shouldmatchers" % "3.2.16" % Test,
  "org.scalatest" %% "scalatest-wordspec" % "3.2.16" % Test,
  "org.scalatest" %% "scalatest-flatspec" % "3.2.16" % Test
)

name := "reflection"
val scalaV = "2.13.11"
scalaVersion := scalaV
val scalaReflection = "org.scala-lang" % "scala-reflect" % scalaV
libraryDependencies += scalaReflection
libraryDependencies += "junit" % "junit" % "4.13.2" % Test
