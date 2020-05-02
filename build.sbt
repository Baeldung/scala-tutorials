ThisBuild / scalaVersion     := "2.12.7"
ThisBuild / version          := "1.0-SNAPSHOT"
ThisBuild / organization     := "com.baeldung"
ThisBuild / organizationName := "core-scala"

lazy val core_scala = (project in file("core-scala"))
  .settings(
    name := "core-scala",
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.5" % Test,
    libraryDependencies += "com.novocode" % "junit-interface" % "0.11" % "test"
  )

lazy val core_scala_oop = (project in file("core-scala-oop"))
  .settings(
    name := "core-scala-oop",
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.5" % Test,
    libraryDependencies += "com.novocode" % "junit-interface" % "0.11" % "test"
  )

lazy val lang_scala = (project in file("lang-scala"))
  .settings(
    name := "lang-scala",
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.5" % Test,
    libraryDependencies += "com.novocode" % "junit-interface" % "0.11" % "test"
  )

lazy val `scala-collections` = (project in file("core-scala-collections"))
  .settings(
    name := "core-scala-collections",
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.5" % Test
  )
