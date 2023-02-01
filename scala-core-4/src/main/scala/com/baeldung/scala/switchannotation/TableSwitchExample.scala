package switchannotation
import scala.annotation.switch

object TableSwitchExample extends App {

  val numWheels = 2

  (numWheels: @switch) match {
    case 1 => println("MonoWheeler")
    case 2 => println("TwoWheeler")
    case 3 => println("ThreeWheeler")
    case 4 => println("FourWheeler")
    case _ => println("UnKnown")
  }

}
