name := "scala-core-8"
val scalaV = "2.13.11"
scalaVersion := scalaV
val scalaTestDeps = Seq(
  "org.scalatest" %% "scalatest" % "3.2.16" % Test,
  "org.scalatest" %% "scalatest-shouldmatchers" % "3.2.16" % Test,
  "org.scalatest" %% "scalatest-wordspec" % "3.2.16" % Test,
  "org.scalatest" %% "scalatest-flatspec" % "3.2.16" % Test
)
val jUnitInterface = "com.github.sbt" % "junit-interface" % "0.13.3" % "test"
val scalaReflection = "org.scala-lang" % "scala-reflect" % scalaV

libraryDependencies += scalaReflection
libraryDependencies ++= scalaTestDeps
libraryDependencies += "org.scala-lang" % "scala-compiler" % scalaVersion.value % "test"
libraryDependencies += "org.scala-lang.modules" %% "scala-java8-compat" % "1.0.2"
libraryDependencies += "com.typesafe" % "config" % "1.2.1"
