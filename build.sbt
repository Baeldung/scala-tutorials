val scalaV = ScalaVersions.scala2Version
val scala3Version = ScalaVersions.scala3Version
ThisBuild / scalaVersion := scalaV
ThisBuild / version := "1.0-SNAPSHOT"
ThisBuild / organization := "com.baeldung"
ThisBuild / organizationName := "core-scala"

lazy val scala_core = (project in file("scala-core"))

lazy val scala_core_2 = (project in file("scala-core-2"))

lazy val scala_core_3 = (project in file("scala-core-3"))

lazy val scala_core_4 = (project in file("scala-core-4"))

lazy val scala_core_5 = (project in file("scala-core-5"))

lazy val scala_core_6 = (project in file("scala-core-6"))

lazy val scala_core_7 = (project in file("scala-core-7"))

lazy val scala_core_8 = (project in file("scala-core-8"))

lazy val scala_core_io = (project in file("scala-core-io"))

lazy val scala_core_oop = (project in file("scala-core-oop"))

lazy val scala_core_fp = (project in file("scala-core-fp"))

lazy val scala_lang = (project in file("scala-lang"))

lazy val scala_lang_2 = (project in file("scala-lang-2"))

lazy val scala_core_collection_2 = (project in file("scala-core-collection-2"))

lazy val scala_core_collections = (project in file("scala-core-collections"))

lazy val scala_core_collections_2 = (project in file("scala-core-collections-2"))

lazy val scala_test = (project in file("scala-test"))

lazy val scala_test_junit4 = (project in file("scala-test-junit4"))

lazy val scala_test_junit5 = (project in file("scala-test-junit-5"))

lazy val scala_akka = (project in file("scala-akka"))

lazy val scala_akka_2 = (project in file("scala-akka-2"))

lazy val scala_libraries = (project in file("scala-libraries"))

lazy val scala_libraries_2 = (project in file("scala-libraries-2"))

lazy val scala_libraries_3 = (project in file("scala-libraries-3"))

lazy val scala_libraries_os = (project in file("scala-libraries-os"))

lazy val scala_libraries_4 = (project in file("scala-libraries-4"))

lazy val scala_libraries_5 = (project in file("scala-libraries-5"))

lazy val scala_strings = (project in file("scala-strings"))

lazy val scala_design_patterns = (project in file("scala-design-patterns"))

lazy val scala3_lang = (project in file("scala3-lang"))

lazy val scala3_lang_2 = (project in file("scala3-lang-2"))

lazy val cats_effects = (project in file("cats-effects"))

lazy val zio = (project in file("zio"))

lazy val doobie = (project in file("doobie"))

// Scala Native Project is disabled as it needs clang to installed in the target machine.
// To test the scala-native code, install clang and then uncomment this build
// lazy val scala_native = (project in file("scala-native"))

// ScalaPy Python Project is disabled as it needs clang and python to installed in the target machine.
// To test this code, install clang, python and then uncommment this build
// lazy val scala_python = (project in file("scala-python"))

lazy val reflection = (project in file("reflection"))

lazy val scala3_libraries = (project in file("scala3-libraries"))

Test / testOptions += Tests.Argument(TestFrameworks.ScalaTest, "-eG")

lazy val scala212 = (project in file("scala-2-modules/scala212"))

addCommandAlias(
  "ci",
  ";clean;compile;test:compile;it:compile;scalafmtCheckAll;test"
)

addCommandAlias(
  "integrationTests",
  """;set ThisBuild/IntegrationTest/testOptions += Tests.Filter(t => !t.endsWith("ManualTest") && !t.endsWith("LiveTest") ); it:test""".stripMargin
)

addCommandAlias(
  "ciFull",
  """;ci; set ThisBuild/IntegrationTest/testOptions += Tests.Filter(t => !t.endsWith("ManualTest") && !t.endsWith("LiveTest") ); it:test""".stripMargin
)

addCommandAlias(
  "manualTests",
  """;ci; set ThisBuild/IntegrationTest/testOptions += Tests.Filter(t => t.endsWith("ManualTest")); it:test""".stripMargin
)

addCommandAlias(
  "liveTests",
  """;ci; set ThisBuild/IntegrationTest/testOptions += Tests.Filter(t => t.endsWith("LiveTest")); it:test""".stripMargin
)

lazy val playGroup = (project in file("play-scala"))
