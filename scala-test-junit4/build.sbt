val scalaTestDeps = Seq(
  "org.scalatest" %% "scalatest" % "3.2.16" % Test,
  "org.scalatest" %% "scalatest-shouldmatchers" % "3.2.16" % Test,
  "org.scalatest" %% "scalatest-wordspec" % "3.2.16" % Test,
  "org.scalatest" %% "scalatest-flatspec" % "3.2.16" % Test
)
val jUnitInterface = "com.github.sbt" % "junit-interface" % "0.13.3" % "test"
val scalaMock = "org.scalamock" %% "scalamock" % "5.2.0" % Test
val scalaV = "2.13.11"
scalaVersion := scalaV
name := "scala-test-junit4"
libraryDependencies ++=
  Seq(
    "org.scalatestplus" %% "junit-4-13" % "3.2.16.0" % Test,
    jUnitInterface
  )