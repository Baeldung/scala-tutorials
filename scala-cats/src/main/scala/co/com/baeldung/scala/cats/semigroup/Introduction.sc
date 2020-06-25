trait Semigroup[A] {
  def combine(x: A, y: A): A
}

// associative law
// combine(x, combine(y, z)) = combine(z, combine(x, y))