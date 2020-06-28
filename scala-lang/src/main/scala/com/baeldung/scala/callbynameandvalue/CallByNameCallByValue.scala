package com.baeldung.scala.callbynameandvalue

/**
 * @author vid2010
 */
object CallByNameCallByValue {
  def getTimeByVal(time: Long) = time

  def getTimeByName(time: => Long) = time

  def add(x: Int, y: => Int) = x + x

}
