package com.baeldung.scala.junit4

import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(classOf[Suite])
@Suite.SuiteClasses(Array(classOf[IntJunitTests], classOf[StringJunitTests]))
class TypesTestSuite
