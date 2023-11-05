package com.baeldung.tapir.server

import com.baeldung.tapir.endpoint.Kitten

object Database {

  var kittens: List[Kitten] = List(
    Kitten(1L, "mew", "male", 20),
    Kitten(2L, "mews", "female", 25),
    Kitten(3L, "smews", "female", 29)
  )

}
