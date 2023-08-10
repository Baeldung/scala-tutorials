val scalaTestDeps = Seq(
  "org.scalatest" %% "scalatest" % "3.2.16" % Test,
  "org.scalatest" %% "scalatest-shouldmatchers" % "3.2.16" % Test,
  "org.scalatest" %% "scalatest-wordspec" % "3.2.16" % Test,
  "org.scalatest" %% "scalatest-flatspec" % "3.2.16" % Test
)
val scalaV = "2.13.11"
scalaVersion := scalaV
val jUnitInterface = "com.github.sbt" % "junit-interface" % "0.13.3" % "test"
val catsEffect = "org.typelevel" %% "cats-effect" % "3.5.1"

name := "scala-core-oop"
libraryDependencies ++= Seq(catsEffect, jUnitInterface) ++ scalaTestDeps