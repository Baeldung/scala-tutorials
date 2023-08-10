name := "scala-core-3"
val scalaTestDeps = Seq(
  "org.scalatest" %% "scalatest" % "3.2.16" % Test,
  "org.scalatest" %% "scalatest-shouldmatchers" % "3.2.16" % Test,
  "org.scalatest" %% "scalatest-wordspec" % "3.2.16" % Test,
  "org.scalatest" %% "scalatest-flatspec" % "3.2.16" % Test
)
val scalaV = "2.13.11"
scalaVersion := scalaV
val scalaMock = "org.scalamock" %% "scalamock" % "5.2.0" % Test
val jUnitInterface = "com.github.sbt" % "junit-interface" % "0.13.3" % "test"
val scalaReflection = "org.scala-lang" % "scala-reflect" % scalaV

libraryDependencies ++= scalaTestDeps
libraryDependencies += jUnitInterface
libraryDependencies += scalaReflection
libraryDependencies += "org.scala-lang.modules" %% "scala-xml" % "2.2.0"