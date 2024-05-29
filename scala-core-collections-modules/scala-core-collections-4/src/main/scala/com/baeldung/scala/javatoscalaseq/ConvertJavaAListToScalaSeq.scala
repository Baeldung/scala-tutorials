package com.baeldung.scala.javatoscalaseq

import java.util
import scala.jdk.javaapi.CollectionConverters
import scala.collection.JavaConverters

object ConvertJavaAListToScalaSeq {

  private def takeSeq(seq: Seq[String]): String = {
    seq.mkString
  }

  def in212(list: util.List[String]): String = {
    val scalaSeq: Seq[String] =
      JavaConverters.collectionAsScalaIterable(list).toSeq
    takeSeq(scalaSeq)
  }

  def in213(list: util.List[String]): String = {
    val scalaSeq: Seq[String] = CollectionConverters.asScala(list).toSeq
    takeSeq(scalaSeq)
  }

  def convertToList212(list: util.List[String]): String = {
    val scalaList: List[String] =
      JavaConverters.collectionAsScalaIterable(list).toList
    val scalaSet: Set[String] =
      JavaConverters.collectionAsScalaIterable(list).toSet
    val scalaIndexedSeq: IndexedSeq[String] =
      JavaConverters.collectionAsScalaIterable(list).toIndexedSeq
    val scalaVector: Vector[String] =
      JavaConverters.collectionAsScalaIterable(list).toVector
    takeSeq(scalaList)
  }

  def convertToList213(list: util.List[String]): String = {
    val scalaList: List[String] = CollectionConverters.asScala(list).toList
    val scalaSet: Set[String] = CollectionConverters.asScala(list).toSet
    val scalaIndexedSeq: IndexedSeq[String] =
      CollectionConverters.asScala(list).toIndexedSeq
    val scalaVector: Vector[String] =
      CollectionConverters.asScala(list).toVector
    takeSeq(scalaList)
  }
}
