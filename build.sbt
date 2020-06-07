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
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.5" % Test,
    libraryDependencies += "org.scalamock" %% "scalamock" % "4.4.0" % Test,
    libraryDependencies += "com.novocode" % "junit-interface" % "0.11" % "test"
  )

lazy val scala_akka = (project in file("scala-akka"))
  .settings(
    name := "scala-akka",
    libraryDependencies += "com.typesafe.akka" %% "akka-actor-typed" % "2.6.5",
    libraryDependencies += "com.typesafe.akka" %% "akka-actor-testkit-typed" % "2.6.5" % Test,
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.5" % Test,
    libraryDependencies += "com.novocode" % "junit-interface" % "0.11" % "test"
  )

val monocleVersion = "2.0.4"
lazy val scala_libraries = (project in file("scala-libraries"))
    .settings(
      name := "scala-libraries",
      libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.5" % Test,
      libraryDependencies ++= Seq(
        "com.github.julien-truffaut" %% "monocle-core" % monocleVersion,
        "com.github.julien-truffaut" %% "monocle-macro" % monocleVersion,
        "com.github.julien-truffaut" %% "monocle-law" % monocleVersion % "test"
      )
   )
