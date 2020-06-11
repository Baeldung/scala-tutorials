

package traitVsAbstractClass

/*
 * trait and abstract class definitions
 */
 abstract class abstClass {
  def myMethod():Unit  
  val aVal: Int  
  val myVal = 10;  
  def myImpMethod:Unit = {    
    println("I am from abstClass")
  }  
}
trait mytrait {
  def myMethod(): Unit
  val aVal: Int
  val myVal = 10;
  def myImpMethod = {
    println("I am from trait")
  }
}


/*
 * constructor parameters for trait and abstract class
 */

//trait myTraitWithParam(name: String){   //compilation error they cant have parameters
//}

abstract class abstClassWithParam(name : String){
}


/*
 * Trait and abstract class for object instance
*/
trait objectInstanceTrait {
  def myOIImpMethod = {
    println("I am from object instanc trait")
  }
}
abstract class objectInstanceAbst {  
  def myOIMethod = {    
    println("I am from object instanc trait")
  }
}


/*
 * traits and abstract class for mixins
 */
abstract class abstClass1 {}

abstract class abstClass2 {}

trait trait1 {}

trait trait2 {}


/*
 * Traits for trait linearization
 */

trait Printer {
  def print(msg: String) = println(msg)
}

trait DelimitWithHyphen extends Printer {
  override def print(msg: String) {
    println("-------------")

  }
}

trait DelimitWithStar extends Printer {
  override def print(msg: String) {
    println("*************")

  }
}


/*
 * Stackable Traits
 * */

trait base {
  def baseMethod(s: String): Unit = println(s)
}

trait stack1 extends base {
  override def baseMethod(s: String) = println("from stack1")
}

//abstract override def baseMethod required in the replace1 because they invoke
//abstract super.baseMethod

trait replace1 extends base {
  abstract override def baseMethod(s: String) = { super.baseMethod(s); println("from replace1") }
}