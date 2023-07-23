// Scala 3 configuration
lazy val Scala3Test = config("scala3Test").extend(Test)
// Source directories
scalaSource in Scala3Test := baseDirectory.value / "src" / "main" / "scala-3"
// Test directories
scalaSource in Scala3Test := baseDirectory.value / "src" / "test" / "scala-3"
