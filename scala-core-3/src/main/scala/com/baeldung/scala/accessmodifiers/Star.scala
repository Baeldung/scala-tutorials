package com.baeldung.scala.accessmodifiers

class Star(val vertexes: Map[Int, (Double, Double)], override val color: String) extends Figure {

  import Star._

  override val lineWidth: Int = 1
  val xList = vertexes.values.toList.map(_._1)
  val yList = vertexes.values.toList.map(_._2)
  override protected val topPoint = yList.max
  override protected val rightMostPoint = xList.max
  val area = areaByVertexes(vertexes)
}

object Star {
  def apply(vertexes: Map[Int, (Double, Double)], color: String) = new Star(vertexes, color)

  private def areaByVertexes(vertexes: Map[Int, (Double, Double)]): Double = ??? // implemented somehow
  //val right = rightMostPoint // Cannot resolve symbol rightMostPoint
}

