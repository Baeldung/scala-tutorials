lazy val customWarts = (project in file("custom-warts")).settings(
  scalaVersion := "2.13.13",
  name := "CustomWarts",
  version := "1.0.0",
  exportJars := true,
  libraryDependencies += "org.wartremover" % "wartremover" % wartremover.Wart.PluginVersion cross CrossVersion.full
)

lazy val root = (project in file("."))
  .dependsOn(customWarts)
  .settings(
    name := "wart-remover",
    scalaVersion := "2.13.13",
    version := "1.0.0",
    wartremoverWarnings ++= Seq(Wart.AsInstanceOf, Wart.Null),
    wartremoverExcluded += baseDirectory.value / "src" / "main" / "scala" / "com" / "baeldung" / "scala" / "wartremover" / "Excluded.scala",
    wartremoverClasspaths ++= {
      (Compile / dependencyClasspath).value.files
        .find(_.name.contains("customwarts"))
        .map(_.toURI.toString)
        .toList
    },
    wartremoverWarnings += Wart.custom(
      "com.baeldung.scala.customwarts.BaeldungWart"
    )
  )
