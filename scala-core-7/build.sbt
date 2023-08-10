name := "scala-core-7"
val scalaV = "2.13.11"
scalaVersion := scalaV
val scalaTestDeps = Seq(
  "org.scalatest" %% "scalatest" % "3.2.16" % Test,
  "org.scalatest" %% "scalatest-shouldmatchers" % "3.2.16" % Test,
  "org.scalatest" %% "scalatest-wordspec" % "3.2.16" % Test,
  "org.scalatest" %% "scalatest-flatspec" % "3.2.16" % Test
)
val jUnitInterface = "com.github.sbt" % "junit-interface" % "0.13.3" % "test"

libraryDependencies ++= scalaTestDeps
libraryDependencies += jUnitInterface
libraryDependencies += "com.github.scopt" %% "scopt" % "4.1.0"
libraryDependencies += "org.rogach" %% "scallop" % "4.1.0"
libraryDependencies += "org.backuity.clist" %% "clist-core" % "3.5.1"
libraryDependencies += "org.backuity.clist" %% "clist-macros" % "3.5.1" % "provided"
libraryDependencies += "args4j" % "args4j" % "2.33"
