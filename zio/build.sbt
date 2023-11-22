val scala3Version = "3.3.0"

scalaVersion := scala3Version

libraryDependencies ++= Seq(
  "dev.zio" %% "zio" % "2.0.15",
  "dev.zio" %% "zio-json" % "0.6.2",
  "dev.zio" %% "zio-http" % "3.0.0-RC2",
  "io.getquill" %% "quill-zio" % "4.6.0",
  "io.getquill" %% "quill-jdbc-zio" % "4.6.0",
  "com.h2database" % "h2" % "2.2.220"
)

libraryDependencies ++= Seq(
  "dev.zio" %% "zio-test" % "2.0.19" % Test,
  "dev.zio" %% "zio-test-sbt" % "2.0.16" % Test,
  "dev.zio" %% "zio-test-magnolia" % "2.0.19" % Test,
  "dev.zio" %% "zio-http-testkit" % "3.0.0-RC2" % Test
)
testFrameworks += new TestFramework("zio.test.sbt.ZTestFramework")

run / fork := true
