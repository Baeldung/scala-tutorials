package com.baeldung.scala.typeinference

object TypeInferenceLimitations extends App{

  val typeInferredVal = 1
  val byteDeclaredVal:Byte = 1

  println(typeInferredVal.getClass)   // prints byte
  println(byteDeclaredVal.getClass)   // prints byte

  def recursiveSum(list:List[Int]):Int={
    if(list.size > 1){
      list.head + recursiveSum(list.drop(1))
    }else{
      list.head
    }
  }

  val sum = recursiveSum(List(1,2,3,4,5))
  println(sum.getClass)   // prints int
}
