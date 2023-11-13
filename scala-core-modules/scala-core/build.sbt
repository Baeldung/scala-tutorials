// Scala 3 configuration
lazy val Scala3Test = config("scala3Test").extend(Test)
// Source directories
Scala3Test / scalaSource := baseDirectory.value / "src" / "main" / "scala-3"
// Test directories
Scala3Test / scalaSource := baseDirectory.value / "src" / "test" / "scala-3"
