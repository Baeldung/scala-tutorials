ThisBuild / scalaVersion := "2.12.7"
ThisBuild / version := "1.0-SNAPSHOT"
ThisBuild / organization := "com.baeldung"
ThisBuild / organizationName := "core-scala"

val scalaTest  = "org.scalatest" %% "scalatest" % "3.1.2" % Test
val junit      = "com.novocode" % "junit-interface" % "0.11" % "test"
val catsEffect = "org.typelevel" % "cats-effect_2.12" % "2.1.4"
val catsCore   = "org.typelevel" % "cats-effect_2.12" % "2.1.4"

lazy val scala_core = (project in file("scala-core"))
  .settings(
    name := "scala-core",
    libraryDependencies ++=
      Seq(
        scalaTest,
        junit,
        catsCore,
        catsEffect
        )
    )

lazy val scala_core_2 = (project in file("scala-core-2"))
  .settings(
    name := "scala-core-2",
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.5" % Test,
    libraryDependencies += "org.scalamock" %% "scalamock" % "4.4.0" % Test,
    libraryDependencies += "com.novocode" % "junit-interface" % "0.11" % "test"
)

lazy val scala_core_3 = (project in file("scala-core-3"))
  .settings(
    name := "scala-core-3",
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.5" % Test,
    libraryDependencies += "com.novocode" % "junit-interface" % "0.11" % "test",
    libraryDependencies += "org.scala-lang" % "scala-reflect" % "2.12.7",
    libraryDependencies += "org.scala-lang.modules" %% "scala-xml" % "2.0.0-M1"
)

lazy val scala_core_4 = (project in file("scala-core-4"))
  .settings(
    name := "scala-core-4",
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.5" % Test,
    libraryDependencies += "com.novocode" % "junit-interface" % "0.11" % "test",
    libraryDependencies += "org.scala-lang" % "scala-reflect" % "2.12.7",
    libraryDependencies += "org.scala-lang.modules" %% "scala-xml" % "2.0.0-M1"
)

lazy val scala_core_io = (project in file("scala-core-io"))
  .settings(
    name := "scala-core-io",
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.5" % Test,
    libraryDependencies += "com.novocode" % "junit-interface" % "0.11" % "test"
  )

lazy val scala_core_oop = (project in file("scala-core-oop"))
  .settings(
    name := "scala-core-oop",
    libraryDependencies ++=
      Seq(
        catsCore,
        scalaTest,
        junit)
  )

lazy val scala_core_fp = (project in file("scala-core-fp"))
  .settings(
    name := "scala-core-fp",
    libraryDependencies ++=
      Seq(
        scalaTest,
        junit)
  )

lazy val scala_lang = (project in file("scala-lang"))
  .settings(
    name := "scala-lang",
    scalacOptions += "-Ypartial-unification",
    libraryDependencies ++=
      Seq(
        scalaTest,
        junit)
  )

lazy val scala_core_collections = (project in file("scala-core-collections"))
  .settings(
    name := "scala-core-collections",
    libraryDependencies +=
      scalaTest
  )

lazy val scala_test = (project in file("scala-test"))
  .settings(
    name := "scala-test",
    libraryDependencies ++=
      Seq(
        scalaTest,
        junit,
        "org.scalamock" %% "scalamock" % "4.4.0" % Test
      )
  )

lazy val scala_akka = (project in file("scala-akka"))
  .settings(
    name := "scala-akka",
    libraryDependencies += "com.typesafe.akka" % "akka-actor-typed_2.12" % "2.6.9",
    libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.2.3",
    libraryDependencies += "com.typesafe.akka" % "akka-actor-testkit-typed_2.12" % "2.6.9" % Test,
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.5" % Test,
    libraryDependencies += "com.novocode" % "junit-interface" % "0.11" % "test"
  )

val monocleVersion = "2.0.4"
val slickVersion   = "3.3.2"
lazy val scala_libraries = (project in file("scala-libraries"))
  .settings(
    name := "scala-libraries",
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.5" % Test,
    libraryDependencies ++= Seq(
      "com.github.julien-truffaut" %% "monocle-core" % monocleVersion,
      "com.github.julien-truffaut" %% "monocle-macro" % monocleVersion,
      "com.github.julien-truffaut" %% "monocle-law" % monocleVersion % "test",
      "com.typesafe.slick" %% "slick" % slickVersion,
      "com.h2database" % "h2" % "1.4.200"
    )
  )

lazy val scala_strings = (project in file("scala-strings"))
  .settings(
    name := "scala-strings",
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.5" % Test,
    libraryDependencies += "com.novocode" % "junit-interface" % "0.11" % "test"
  )

lazy val scala_design_patterns = (project in file("scala-design-patterns"))
  .settings(
    name := "scala-design-patterns",
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.5" % Test,
    libraryDependencies += "org.scalamock" %% "scalamock" % "4.4.0" % Test,
    libraryDependencies += "com.novocode" % "junit-interface" % "0.11" % "test",
  )

