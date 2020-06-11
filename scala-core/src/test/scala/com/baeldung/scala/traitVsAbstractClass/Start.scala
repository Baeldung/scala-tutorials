
package traitVsAbstractClass

object start {

  def main(args: Array[String]): Unit = {

    // val myTObj =  new mytrait //trait cant be instanciated
    //  val myAObj = new abstClass() //abstract class cant be instanciated

    val myTraitClassObj = new myTClass
    val myAbstClassObj = new myAClass

    val classObjWithTrait = new myTClass with objectInstanceTrait
    //val classObjWithAbst = new myTClass with objectInstanceAbst  // abstract class cant be added to instance of object

    myTraitClassObj.myImpMethod
    myTraitClassObj.myMethod()

    myAbstClassObj.myImpMethod
    myAbstClassObj.myMethod()

    classObjWithTrait.myOIImpMethod

    val obje = new CustomPrinter().print("Hello World!")

    (new Stackable with stack1 with replace1)()

  }
}

class myTClass extends mytrait {
  val aVal = 10;
  def myMethod = { println("I am from myTclass") }
}
class myAClass extends abstClass {
  val aVal = 10;
  def myMethod = { println("I am from myAclass") }
}


class myClass extends trait1 with trait2 {
}

class CustomPrinter extends Printer with DelimitWithHyphen with DelimitWithStar

class Stackable {
  this: base =>
  def apply() {
    println(
      baseMethod("bottom"))
  }
}









