lazy val validateUnitTestNames =
  taskKey[Int]("Validate test naming convention for unit tests")
val inAllUnitTest = ScopeFilter(inAnyProject, inConfigurations(Test))
validateUnitTestNames := {
  val testFileFullPath: Seq[Seq[String]] =
    definedTestNames.all(inAllUnitTest).value
  val log = streams.value.log
  val invalidTestName = testFileFullPath
    .flatMap(_.filterNot(_.endsWith("UnitTest")))
    .filterNot(_.trim.isEmpty)
  val invalidTestNameFormatted = invalidTestName.mkString("\n")
  if (invalidTestName.nonEmpty) {
    log.error(
      s"""Found unit test files not matching with naming standards. Unit test files must end with UnitTest.scala.
         | Invalid files:
         | ${invalidTestNameFormatted}
         |""".stripMargin
    )
  }
  require(
    invalidTestName.isEmpty,
    s"Found ${invalidTestName.size} tests that doesn't follow naming convention!"
  )
  0
}

// for integration tests
//
//lazy val validateIntegrationTestNames = taskKey[Int]("Validate test naming convention for unit tests")
//val inAllIntegrationTest = ScopeFilter(inAnyProject, inConfigurations(IntegrationTest))
//validateIntegrationTestNames := {
//  val testFileFullPath: Seq[Seq[String]] = definedTestNames.all(inAllIntegrationTest).value
//  val log = streams.value.log
//  val invalidTestName = testFileFullPath.flatMap(_.filterNot(_.endsWith("IntegrationTest"))).filterNot(_.trim.isEmpty)
//  val invalidTestNameFormatted = invalidTestName.mkString("\n")
//  log.error(
//    s"""Found integration test files not matching with naming standards. Integration test files must end with IntegrationTest.scala.
//       | Invalid files:
//       | ${invalidTestNameFormatted}
//       |""".stripMargin)
//  require(invalidTestName.isEmpty, s"Found ${invalidTestName.size} tests that doesn't follow naming convention!")
//  0
//}
