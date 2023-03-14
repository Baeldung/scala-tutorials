package com.baeldung.scala.pathdependenttypes

import java.nio.ByteBuffer
import scala.collection.mutable

abstract class Key(val name: String) {
  type ValueType
}

trait Operations {
  def set(key: Key)(v: key.ValueType)(implicit
    enc: Encoder[key.ValueType]
  ): Unit
  def get(key: Key)(implicit
    decoder: Decoder[key.ValueType]
  ): Option[key.ValueType]
}

case class Database() extends Operations {
  private val db = mutable.Map.empty[String, Array[Byte]]

  def set(key: Key)(value: key.ValueType)(implicit
    enc: Encoder[key.ValueType]
  ): Unit =
    db.update(key.name, enc.encode(value))

  def get(
    key: Key
  )(implicit decoder: Decoder[key.ValueType]): Option[key.ValueType] = {
    db.get(key.name).map(x => decoder.encode(x))
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
