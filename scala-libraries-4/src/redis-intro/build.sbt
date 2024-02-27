import Dependencies._

ThisBuild / scalaVersion := "2.13.13"
ThisBuild / version := "1.0.0"
ThisBuild / organization := "com.baeldung"

lazy val root = (project in file("."))
  .settings(
    name := "Redis Intro",
    libraryDependencies += redisClients,
    libraryDependencies += jacksonScala,
    libraryDependencies += scalaTest % Test,
    libraryDependencies += mockito % Test
  )
