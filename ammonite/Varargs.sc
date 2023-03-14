@main
def main(fruits: String*) = {
  fruits foreach {println(_)}
}