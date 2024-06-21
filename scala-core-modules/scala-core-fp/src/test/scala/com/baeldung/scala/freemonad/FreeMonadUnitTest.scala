package com.baeldung.scala.freemonad

import com.baeldung.scala.freemonad.{*, given}

import scala.concurrent.Future

import org.scalatest.concurrent.ScalaFutures
import org.scalatest.freespec.AsyncFreeSpecLike
import org.scalatest.matchers.should.Matchers

class FreeMonadUnitTest
  extends AsyncFreeSpecLike
  with Matchers
  with ScalaFutures:

  "sumProgram should be transformed as a free structure into it's value using a proper monad" in:
    sumProgram
      .foldMapAs[Future](using FutureMonad, LazyCatchable2Future)
      .map(_ shouldBe 3)

  "BaeldungWorkflowInterpreter should preserve logic on non-workflow types" in:
    sumProgram
      .foldMapAs[Future](using FutureMonad, BaeldungWorkflowInterpreter)
      .map(_ shouldBe 3)

  "should demonstrate for-comprehension and flatMap/map equivalence" in:
    listComposition shouldBe desugaredListComposition

  "joinBaeldungWorkflows spec should succeed" in:
    joinBaeldungWorkflow
      .foldMapAs[Future](using FutureMonad, LazyCatchable2Future)
      .map(_ shouldBe WorkflowCommand.JoinBaeldungAsAWriter)