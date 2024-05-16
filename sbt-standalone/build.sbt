name := "SCALA-156"

version := "0.1"

scalaVersion := "2.13.6"

// Setting target for Scala
scalacOptions += "-target:17"

// Setting source and target for Java
javacOptions ++= Seq("-source", "17", "-target", "17")

// Enforcing the minimum JVM version
initialize := {
  val _ = initialize.value // Ensure previous initializations are run

  val required = VersionNumber("17")
  val current = VersionNumber(sys.props("java.specification.version"))
  println(current)
  assert(
    CompatibleJavaVersion(current, required),
    s"Java $required or above is required to run this project."
  )
}
