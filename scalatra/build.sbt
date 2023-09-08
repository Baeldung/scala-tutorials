val ScalatraVersion = "2.8.2"

ThisBuild / scalaVersion := "2.13.10"
ThisBuild / organization := "baeldung"

name := "scalatratutorial"
version := "0.1.0-SNAPSHOT"
libraryDependencies ++= Seq(
  "org.scalatra" %% "scalatra" % ScalatraVersion,
  "org.scalatra" %% "scalatra-scalatest" % ScalatraVersion % "test",
  "ch.qos.logback" % "logback-classic" % "1.2.3" % "runtime",
  "org.eclipse.jetty" % "jetty-webapp" % "9.4.52.v20230823" % "container",
  "javax.servlet" % "javax.servlet-api" % "3.1.0" % "provided",
  "org.scalatra" %% "scalatra-auth" % ScalatraVersion,
  "org.scalatra" %% "scalatra-json" % "2.8.2",
  "org.json4s" %% "json4s-jackson" % "4.0.1",
  "com.typesafe.slick" %% "slick" % "3.3.2",
  "com.h2database" % "h2" % "1.4.196",
  "com.mchange" % "c3p0" % "0.9.5.2"
)

enablePlugins(SbtTwirl)
enablePlugins(JettyPlugin)
