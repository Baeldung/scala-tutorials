// Scala 3 configuration
lazy val Scala3Test = config("scala3Test").extend(Test)
// Source directories
Scala3Test / scalaSource := baseDirectory.value / "src" / "main" / "scala-3"
// Test directories
Scala3Test / scalaSource := baseDirectory.value / "src" / "test" / "scala-3"

name := "scala-core"
val scalaV = "2.13.11"
scalaVersion := scalaV
val scalaTestDeps = Seq(
  "org.scalatest" %% "scalatest" % "3.2.16" % Test,
  "org.scalatest" %% "scalatest-shouldmatchers" % "3.2.16" % Test,
  "org.scalatest" %% "scalatest-wordspec" % "3.2.16" % Test,
  "org.scalatest" %% "scalatest-flatspec" % "3.2.16" % Test
)

libraryDependencies ++= Seq(
  "com.github.sbt" % "junit-interface" % "0.13.3" % "test",
  "org.typelevel" %% "cats-effect" % "3.5.1"
) ++ scalaTestDeps
