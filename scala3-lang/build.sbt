val scala3Version = "3.0.0-RC3"

scalaVersion := scala3Version

testFrameworks += new TestFramework("munit.Framework")

libraryDependencies ++= Seq(
  "org.scalameta" %% "munit" % "0.7.25"
)
