package com.baeldung.scala.higherkindedtypes

import org.scalatest.{Matchers, WordSpec}
import com.baeldung.scala.higherkindedtypes.HigherKindedTypes._

class HigherKindedTypesUnitTest extends  WordSpec with Matchers {

  "Collections" should {
    "accept any type creator" in {
      val listCollection = new Collection[List] {
        override def append[A](a: A): List[A] = List(a)
        override def pop[B](b: List[B]): B = b.head
      }
      listCollection.append("Some values") shouldBe(List("Some values"))
      listCollection.pop(List("Some values")) shouldBe("Some values")

      val seqCollection = new Collection[Seq] {
        override def append[A](a: A): Seq[A] = Seq(a)
        override def pop[B](b: Seq[B]): B = b.head
      }
      seqCollection.append("Some values") shouldBe(Seq("Some values"))
      seqCollection.pop(Seq("Some values")) shouldBe("Some values")
    }
  }

  "BatchRun" should {
    "write any given data into a given container" in {
      val listDb: List[String] = List("data 1", "data 2")
      var listBatchRun = new BatchRun[List] {
        def transform[A](item: A, db: List[A]): List[A] = db ::: item :: Nil
      }
      val savedList = listBatchRun.write("data 3", listDb)
      savedList shouldEqual(List("data 1", "data 2", "data 3"))


      val seqDb: Seq[Int] = Seq(1, 2)
      val seqBatchRun = new BatchRun[Seq] {
        def transform[A](item: A, db: Seq[A]): Seq[A] = db :+ item
      }
      val savedSeq = seqBatchRun.write(3, seqDb)
      savedSeq shouldEqual(Seq(1, 2, 3))
    }
  }


}
