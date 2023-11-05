package switchannotation
import scala.annotation.switch

object LookupSwitchExample extends App {

  val alphabet = 'A'

  (alphabet: @switch) match {
    case 'A' => println("ANIMAL")
    case 'E' => println("ELEPHANT")
    case 'I' => println("IRON")
    case 'O' => println("OWL")
    case 'U' => println("UMBRELLA")
    case _   => println("Not a Vowel")
  }

}
