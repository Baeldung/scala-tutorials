val ScalatraVersion = "2.8.4"

ThisBuild / scalaVersion := "2.13.12"
ThisBuild / organization := "baeldung"

name := "scalatratutorial"
version := "0.1.0-SNAPSHOT"
libraryDependencies ++= Seq(
  "org.scalatra" %% "scalatra" % ScalatraVersion,
  "org.scalatra" %% "scalatra-scalatest" % ScalatraVersion % "test",
  "ch.qos.logback" % "logback-classic" % "1.4.11" % "runtime",
  "org.eclipse.jetty" % "jetty-webapp" % "11.0.18" % "container",
  "javax.servlet" % "javax.servlet-api" % "4.0.1" % "provided",
  "org.scalatra" %% "scalatra-auth" % ScalatraVersion,
  "org.scalatra" %% "scalatra-json" % "2.8.4",
  "org.json4s" %% "json4s-jackson" % "4.0.6",
  "com.typesafe.slick" %% "slick" % "3.3.3",
  "com.h2database" % "h2" % "1.4.196",
  "com.mchange" % "c3p0" % "0.9.5.5"
)
libraryDependencySchemes += "org.scala-lang.modules" %% "scala-xml" % VersionScheme.Always
enablePlugins(SbtTwirl)
enablePlugins(JettyPlugin)
