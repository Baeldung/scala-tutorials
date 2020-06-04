ThisBuild / scalaVersion     := "2.12.7"
ThisBuild / version          := "1.0-SNAPSHOT"
ThisBuild / organization     := "com.baeldung"
ThisBuild / organizationName := "core-scala"

lazy val scala_core = (project in file("scala-core"))
  .settings(
    name := "scala-core",
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.5" % Test,
    libraryDependencies += "com.novocode" % "junit-interface" % "0.11" % "test"
  )

lazy val scala_core_oop = (project in file("scala-core-oop"))
  .settings(
    name := "scala-core-oop",
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.5" % Test,
    libraryDependencies += "com.novocode" % "junit-interface" % "0.11" % "test"
  )

lazy val scala_core_fp = (project in file("scala-core-fp"))
  .settings(
    name := "scala-core-fp",
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.5" % Test,
    libraryDependencies += "com.novocode" % "junit-interface" % "0.11" % "test"
  )

lazy val scala_lang = (project in file("scala-lang"))
  .settings(
    name := "scala-lang",
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.5" % Test,
    libraryDependencies += "com.novocode" % "junit-interface" % "0.11" % "test"
  )

lazy val scala_core_collections = (project in file("scala-core-collections"))
  .settings(
    name := "scala-core-collections",
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.5" % Test
  )

lazy val scala_test = (project in file("scala-test"))
  .settings(
    name := "scala-test",
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.1.1" % Test,
    libraryDependencies += "org.scalamock" %% "scalamock" % "4.4.0" % Test,
    libraryDependencies += "com.novocode" % "junit-interface" % "0.11" % "test"
  )
