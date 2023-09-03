import Dependencies._

ThisBuild / scalaVersion := "2.13.11"
ThisBuild / version := "0.1.0-SNAPSHOT"

lazy val printHello = taskKey[Unit]("prints hello")
printHello := println("hello")

lazy val one = taskKey[Int]("one")
one := 1

lazy val oneTimesTwo = taskKey[Unit]("one times two")
oneTimesTwo := println(s"2 * 1 = ${2 * one.value}")

lazy val helloCommand = Command.command("helloCommand")(state => {
  println("hello from command")
  state
})

lazy val helloCommand2 = Command.command("helloCommand2")(state => {
  println("hello from command 2")
  state.copy(definedCommands = state.definedCommands :+ helloCommand)
})

lazy val root = (project in file("."))
  .settings(
    name := "intro-to-sbt",
    libraryDependencies += scalaTest % Test,
    commands ++= Seq(helloCommand2)
  )

resolvers ++= Seq(
  "Typesafe" at "https://repo.typesafe.com/typesafe/releases/",
  "Java.net Maven2 Repository" at "https://download.java.net/maven/2/"
)

addCommandAlias("compileAndTest", "compile;test;")
