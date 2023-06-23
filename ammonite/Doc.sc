@arg(doc = "Sum two numbers and print out the result")
@main
def add(
  @arg(doc = "The first number")
  n1: Int,
  @arg(doc = "The second number")
  n2: Int
): Unit = {
  println(s"$n1 + $n2 = ${n1 + n2}")
}
