package com.baeldung.scala.javatoscalaseq

import java.util
import scala.jdk.javaapi.CollectionConverters
import scala.collection.JavaConverters._

object ConvertJavaAListToScalaSeq {

  private def takeSeq(seq: Seq[String]): String = {
    seq.mkString
  }

  def in212(list: util.List[String]): String = {
    val scalaList: Seq[String] = list.asScala.toSeq
    takeSeq(scalaList)
  }

  def in213(list: util.List[String]): String = {
    val scalaList: Seq[String] = CollectionConverters.asScala(list).toSeq
    takeSeq(scalaList)
  }
}
