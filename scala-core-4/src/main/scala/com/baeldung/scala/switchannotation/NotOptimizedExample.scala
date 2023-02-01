package switchannotation
import scala.annotation.switch

object NotOptimizedExample extends App {

  val gender = 1
  val male = 0
  val female = 1

  (gender: @switch) match { // Compiler gives a warning: could not emit switch for @switch annotated match
    case `male`   => println("Male")
    case `female` => println("Female")
    case _        => println("Invalid")
  }

}
