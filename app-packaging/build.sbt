name := "app-packaging"
version := "1.0.2"

ThisBuild / scalaVersion := "3.1.0"

libraryDependencies ++= Seq(
  "com.lihaoyi" %% "os-lib" % "0.7.8"
)

val mainClassPath = "com.baeldung.packaging.mainMethod"

// sbt-assembly configurations
assembly / assemblyJarName := "assemblyApp.jar"
assembly / mainClass := Some(mainClassPath) //since @main method name will be the class name of the main class in scala 3

// sbt native packager settings
enablePlugins(JavaAppPackaging)
enablePlugins(JDKPackagerPlugin)
maintainer := "Yadukrishnan <yadavan88@gmail.com>"
Compile / mainClass := Some(mainClassPath)
maintainer := "yadavan88@gmail.com"

enablePlugins(JlinkPlugin)

jlinkIgnoreMissingDependency := JlinkIgnore.only(
  "scala.quoted" -> "scala",
  "scala.quoted.runtime" -> "scala"
)

SBT Proguard plugin
enablePlugins(SbtProguard)
Proguard / proguardOptions ++= Seq("-dontoptimize","-dontnote", "-dontwarn", "-ignorewarnings")
Proguard / proguardOptions += ProguardOptions.keepMain("com.yadavan88.app.mainMethod")
Proguard / proguardInputs := (Compile / dependencyClasspath).value.files
Proguard / proguardFilteredInputs ++= ProguardOptions.noFilter((Compile / packageBin).value)
