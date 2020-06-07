package com.baeldung.scala.abstractclassvstraits

/**
  * Example code to illustrate abstract classes as composition mechanism
  * @author Oscar Forero
  */
object TraitsGeometry {

  /**
    *  A trait representing a geometrical Entity
    */  
  trait Entity {
      def name: String
  }

  /**
    * A trait representing the origin of a geometrical Entity
    */
  trait Origin {
      def origin: Point
  }

  /**
    * A trait representing something that has an Area
    */
  trait Area {
      def area: Int 
  }

  /**
    * A trait representing a 2D shape
    */
  trait Shape2D extends Entity with Origin with Area

  /**
    * A trait representing something that is drawable
    */
  trait Drawable {
      def draw(): String
  }

  /**
    * A trait that draws a geometrical entity 
    */
  trait DrawableEntity extends Drawable {
      this: Entity =>
        def draw(): String = name
  }

  /**
    * A trait that draws a geometrical entity with an origin
    */
  trait DrawableEntityWithOrigin extends DrawableEntity {
      this: Entity with Origin =>
        override def draw(): String = s"$name at $origin"
  }

  /**
    * A trait that draws a 2D shape  
    */
    trait Drawable2DShape extends Drawable {
      this: Shape2D =>
        override def draw(): String = s"$name at $origin with $area area"
  }

/**
  * A concrete class representing a point
  * Build composing an Entity with an Origin with the appropriate Drawable trait 
  *
  * @param x
  * @param y
  */
  class Point(val x: Int, val y: Int) extends Entity with Origin with DrawableEntityWithOrigin {
      val name: String = "Point"
      val origin = this
      override def toString(): String = s"($x, $y)"
  }

  /**
  * A concrete class representing a square
  * Build composing a 2D shape with the appropriate Drawable trait 
  * @param x
    * @param y
    * @param side
    */
  class Square(x: Int, y: Int, val side: Int) extends Shape2D with Drawable2DShape {
      val name = "Square"
      val origin = new Point(x, y)
      val area = side * side
  }

}
