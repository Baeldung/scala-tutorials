package com.baeldung.scala.abstractclassvstraits

/**
  * Example code to illustrate the limitation of abstract classes
  * @author Oscar Forero
  */
object AbstractClassGeometry {

  /**
    * An abstract class representing something that can be draw
    */
  abstract class Drawable {
    def draw(): Unit
  }

  /**
    * And abstract class representing a Geometric entity
    *
    * @param name The name of the entity (E.g. "Point")
    */
  abstract class Entity(name: String)

  /**
    * We can't extend Entity and Drawable forcing us to mix hierarchies 
    * or to use only interface inheritance, losing the ability to reuse code.
    *
    * @param name
    * @param origin
    */
  abstract class Shape2D(name: String, origin: (Int, Int))
      extends Entity(name)
      //  
      {
    def area: Int
    /*override*/
    def draw(): Unit = println(s"Drawing $name at (${origin._1} ${origin._2})")
  }

  /**
    * Should we extend Shape to reuse the drawing code?
    * if so what do we do about area, which is not well defined for a point?
    */
  class Point extends Entity("Point")
}
