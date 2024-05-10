lazy val validateTestNames = taskKey[Int]("Validate test naming convention")
val inAllTest = ScopeFilter(inAnyProject, inConfigurations(Test))
validateTestNames := {
  val testFileFullPath: Seq[Seq[String]] = definedTestNames.all(inAllTest).value
  val log = streams.value.log
  val invalidTestName = testFileFullPath.flatMap(_.filterNot(_.endsWith("UnitTest")))
  val invalidTestNameFormatted = invalidTestName.mkString("\n")
  log.error(
    s"""Found unit test files not matching with naming standards. Unit test files must end with UnitTest.scala.
       | Invalid files:
       | ${invalidTestNameFormatted}
       |""".stripMargin)
  require(invalidTestName.isEmpty, s"Found ${invalidTestName.size} tests that doesn't follow naming convention!")
  0
}