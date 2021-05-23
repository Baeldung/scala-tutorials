val scala3Version = "3.0.0"

lazy val root = project
  .in(file("."))
  .settings(
    scalaVersion := scala3Version,
    libraryDependencies ++= Seq("org.scalatest" %% "scalatest" % "3.2.9" % Test)
  )
