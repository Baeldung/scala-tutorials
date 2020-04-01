ThisBuild / scalaVersion     := "2.12.7"
ThisBuild / version          := "1.0-SNAPSHOT"
ThisBuild / organization     := "com.baeldung"
ThisBuild / organizationName := "core-scala"

lazy val root = (project in file("core-scala"))
  .settings(
    name := "core-scala",
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.5" % Test,
    libraryDependencies += "org.scalamock" %% "scalamock" % "4.4.0" % Test,
    libraryDependencies += "com.novocode" % "junit-interface" % "0.11" % "test"
  )
