package com.baeldung.scala.tastyfiles

import com.baeldung.scala.tastyfiles.*

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class PowerMacroUnitTest extends AnyFlatSpec with Matchers:

  "PowerMacro.showAsPowerTerm" should "generate compile-time term structure" in:
    val expr: String = PowerMacro.showAsPowerTerm(2.0, 3)

    expr == """
      Inlined(Ident(PowerMacro$),List(),Apply(Select(Inlined(EmptyTree,List(),Inlined(EmptyTree,List(),Literal(Constant(2.0)))),*),List(Inlined(EmptyTree,List(),Inlined(Ident(PowerMacro$),List(),Apply(Select(Inlined(EmptyTree,List(),Inlined(EmptyTree,List(),Literal(Constant(2.0)))),*),List(Inlined(EmptyTree,List(),Inlined(EmptyTree,List(),Literal(Constant(2.0)))))))))))
    """.trim
