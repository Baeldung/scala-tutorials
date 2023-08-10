val scalaTestDeps = Seq(
  "org.scalatest" %% "scalatest" % "3.2.16" % Test,
  "org.scalatest" %% "scalatest-shouldmatchers" % "3.2.16" % Test,
  "org.scalatest" %% "scalatest-wordspec" % "3.2.16" % Test,
  "org.scalatest" %% "scalatest-flatspec" % "3.2.16" % Test
)

name := "scala-core-collections"
libraryDependencies ++= Seq(
  "org.scala-lang.modules" %% "scala-parallel-collections" % "1.0.4"
) ++ scalaTestDeps
val scalaV = "2.13.11"
scalaVersion := scalaV