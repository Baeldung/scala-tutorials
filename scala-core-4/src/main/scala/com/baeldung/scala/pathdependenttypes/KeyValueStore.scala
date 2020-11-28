package com.baeldung.scala.pathdependenttypes

import java.nio.ByteBuffer

import scala.collection.mutable

object KeyValueStore extends App {
  private val db = Database()
  private val k = key[String]("key")
  
  db.set(k)("Hello")
  assert(db.get(key[String]("key")).exists(_.isInstanceOf[String]))
}


abstract class Key(val name: String) {
  type ValueType
}

case class Database() {
  private val db = mutable.Map.empty[String, Array[Byte]]

  def set(k: Key)(v: k.ValueType)(implicit enc: Encoder[k.ValueType]): Unit =
    db.update(k.name, enc.encode(v))

  def get(
    k: Key
  )(implicit decoder: Decoder[k.ValueType]): Option[k.ValueType] = {
    db.get(k.name).map(x => decoder.encode(x))
  }

}

object Database {
  def key[Data](v: String) =
    new Key(v) {
      override type ValueType = Data
    }
}

trait Encoder[T] {
  def encode(t: T): Array[Byte]
}

object Encoder {
  implicit val stringEncoder: Encoder[String] = new Encoder[String] {
    override def encode(t: String): Array[Byte] = t.getBytes
  }
  implicit val doubleEncoder: Encoder[Double] = new Encoder[Double] {
    override def encode(t: Double): Array[Byte] = {
      val bytes = new Array[Byte](8)
      ByteBuffer.wrap(bytes).putDouble(t)
      bytes
    }
  }
}

trait Decoder[T] {
  def encode(d: Array[Byte]): T
}

object Decoder {
  implicit val stringDecoder: Decoder[String] = (d: Array[Byte]) =>
    new String(d)
  implicit val intDecoder: Decoder[Double] = (d: Array[Byte]) =>
    ByteBuffer.wrap(d).getDouble
}
