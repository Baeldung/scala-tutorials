name := "scala_js"
organization := "com.baeldung"

version := "1.0-SNAPSHOT"

enablePlugins(ScalaJSPlugin)

scalaVersion := "2.13.11"

scalaJSUseMainModuleInitializer := true
libraryDependencies += "org.scala-js" %%% "scalajs-dom" % "2.2.0"
libraryDependencies += "org.scalatest" %%% "scalatest" % "3.1.2" % Test

//enable the below setting after installing npm package jsdom.
jsEnv := new org.scalajs.jsenv.jsdomnodejs.JSDOMNodeJSEnv()
