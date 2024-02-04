val ScalatraVersion = "2.8.4"

ThisBuild / scalaVersion := "2.13.12"
ThisBuild / organization := "baeldung"

name := "scalatratutorial"
version := "0.1.0-SNAPSHOT"
libraryDependencies ++= Seq(
  "org.scalatra" %% "scalatra" % ScalatraVersion,
  "org.scalatra" %% "scalatra-scalatest" % ScalatraVersion % "test",
  "ch.qos.logback" % "logback-classic" % "1.4.14" % "runtime",
  "org.eclipse.jetty" % "jetty-webapp" % "11.0.20" % "container",
  "javax.servlet" % "javax.servlet-api" % "4.0.1" % "provided",
  "org.scalatra" %% "scalatra-auth" % ScalatraVersion,
  "org.scalatra" %% "scalatra-json" % "2.8.4",
  "org.json4s" %% "json4s-jackson" % "4.0.7",
  "com.typesafe.slick" %% "slick" % "3.4.1",
  "com.h2database" % "h2" % "2.1.214",
  "com.mchange" % "c3p0" % "0.9.5.5"
)
libraryDependencySchemes += "org.scala-lang.modules" %% "scala-xml" % VersionScheme.Always
enablePlugins(SbtTwirl)
enablePlugins(JettyPlugin)
