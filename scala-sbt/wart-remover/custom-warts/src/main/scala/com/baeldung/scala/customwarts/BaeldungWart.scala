package com.baeldung.scala.customwarts

import org.wartremover.{WartTraverser, WartUniverse}

object BaeldungWart extends WartTraverser {
  def apply(u: WartUniverse): u.Traverser = {
    import u.universe._

    new Traverser {
      override def traverse(tree: Tree): Unit = {
        tree match {
          case t
              if hasWartAnnotation(u)(
                t
              ) => // Ignore trees with the SuppressWarnings annotation
          case Literal(Constant("Baeldung")) =>
            error(u)(tree.pos, "Baeldung literal is disabled")
          case _ => super.traverse(tree)
        }
      }
    }
  }
}
