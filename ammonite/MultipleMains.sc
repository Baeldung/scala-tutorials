@main
def funA(arg: String): Unit = {
  println(s"""funA called with param "$arg"""")
}

@main
def funB(n: Int): Unit = {
  println(s"""funB called with param "$n"""")
}