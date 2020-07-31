package com.baeldung.scala.functor

object Tree {
  sealed abstract class Tree[+A]
  case class Leaf[A](value: A) extends Tree[A]
  case class Node[A](left: Tree[A], right: Tree[A]) extends Tree[A] 
}
