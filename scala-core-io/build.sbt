val scalaTestDeps = Seq(
  "org.scalatest" %% "scalatest" % "3.2.16" % Test,
  "org.scalatest" %% "scalatest-shouldmatchers" % "3.2.16" % Test,
  "org.scalatest" %% "scalatest-wordspec" % "3.2.16" % Test,
  "org.scalatest" %% "scalatest-flatspec" % "3.2.16" % Test
)
val scalaV = "2.13.11"
scalaVersion := scalaV
val jUnitInterface = "com.github.sbt" % "junit-interface" % "0.13.3" % "test"
val scalaReflection = "org.scala-lang" % "scala-reflect" % scalaV

name := "scala-core-io"
libraryDependencies ++= scalaTestDeps
libraryDependencies += jUnitInterface
libraryDependencies += "org.scala-lang" % "scala-compiler" % scalaVersion.value