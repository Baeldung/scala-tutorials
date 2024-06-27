name := "scala_js"
organization := "com.baeldung"

version := "1.0-SNAPSHOT"

enablePlugins(ScalaJSPlugin)

scalaJSUseMainModuleInitializer := true
libraryDependencies += "org.scala-js" %%% "scalajs-dom" % "2.8.0"
libraryDependencies += "org.scalatest" %%% "scalatest" % "3.2.19" % Test

//enable the below setting after installing npm package jsdom.
jsEnv := new org.scalajs.jsenv.jsdomnodejs.JSDOMNodeJSEnv()
