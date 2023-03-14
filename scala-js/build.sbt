name := "scala_js"
organization := "com.baeldung"

version := "1.0-SNAPSHOT"

lazy val root = (project in file("."))
  .enablePlugins(ScalaJSPlugin)
  .settings(
    jsEnv := new org.scalajs.jsenv.jsdomnodejs.JSDOMNodeJSEnv()
  )

scalaVersion := "2.13.10"

scalaJSUseMainModuleInitializer := true
libraryDependencies += "org.scala-js" %%% "scalajs-dom" % "2.2.0"
libraryDependencies += "org.scalatest" %%% "scalatest" % "3.1.2" % Test

