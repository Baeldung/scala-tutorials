package com.baeldung.scala.shapeless

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import shapeless.ops.record.Keys
import shapeless.{Generic, HNil, Inl, Inr, _}

class ShapelessSpecs extends AnyWordSpec with Matchers {
  val hlist: Int :: Double :: String :: Boolean :: HNil =
    1 :: 1.0 :: "One" :: false :: HNil

  case class User(name: String, age: Int)

  "HList" should {
    "return the first element of hlist" in {
      assert(hlist.head == 1)
    }

    "return the two frist elements of hlist" in {
      assert(hlist.take(2) == 1 :: 1.0 :: HNil)
    }

    "return the tail of hlist" in {
      assert(hlist.tail == 1.0 :: "One" :: false :: HNil)
    }
  }

  "Coproduct" should {
    sealed trait TrafficLight
    case class Green() extends TrafficLight
    case class Red() extends TrafficLight
    case class Yellow() extends TrafficLight

    "convert sealed trait coproduct to shapeless coproduct" in {
      val gen    = Generic[TrafficLight]
      val green  = gen.to(Green())
      val red    = gen.to(Red())
      val yellow = gen.to(Yellow())
      assert(green == Inl(Green()))
      assert(red == Inr(Inl(Red())))
      assert(yellow == Inr(Inr(Inl(Yellow()))))
    }

    "convert back shapeless coproduct to sealed trait product type" in {
      val gen    = Generic[TrafficLight]
      val green  = gen.from(Inl(Green()))
      val red    = gen.from(Inr(Inl(Red())))
      val yellow = gen.from(Inr(Inr(Inl(Yellow()))))
      assert(green == Green())
      assert(red == Red())
      assert(yellow == Yellow())
    }

    "create coproduct" in {
      import shapeless._
      object Green
      object Red
      object Yellow
      type Light = Green.type :+: Red.type :+: Yellow.type :+: CNil
      val light = Coproduct[Light](Green)
      light.filter
      assert(light.select[Green.type] == Some(Green))
      assert(light.select[Yellow.type] == None)
    }

  }

  "Generic" should {
    "Convert case class to corresponding HList product type" in {
      val user      = User("John", 25)
      val userHList = Generic[User].to(user)
      assert(userHList == "John" :: 25 :: HNil)
    }

    "Convert back hlist to corresponding case class product type" in {
      val user       = User("John", 25)
      val userHList  = Generic[User].to(user)
      val userRecord = Generic[User].from(userHList)
      assert(user == userRecord)
    }

    "Convert tuple to HList product type" in {
      val user      = ("John", 25)
      val userHList = Generic[(String, Int)].to(user)
      assert(userHList == "John" :: 25 :: HNil)
    }

    "Operations on tuple" should {
      import shapeless.syntax.std.tuple._
      val tuple = ("John", 25, true)

      "take head from tuple" in {
        val head = tuple.head
        assert(head == "John")
      }

      "drop two element from tuple" in {
        val res = tuple.drop(2)
        assert(res == Tuple1(true))
      }

      "concat two tuple to one" in {
        val list = tuple ++ (1.3, "foo")
        assert(list == ("John", 25, true, 1.3, "foo"))
      }
    }

    "Convert back hlist to corresponding tuple product type" in {
      val user       = ("John", 25)
      val userHList  = Generic[(String, Int)].to(user)
      val userRecord = Generic[(String, Int)].from(userHList)
      assert(user == userRecord)
    }
  }

  "LabelledGeneric" should {
    import shapeless._
    import record._
    val user               = User("John", 25)
    val userGen            = LabelledGeneric[User]
    val userLaballedRecord = userGen.to(user)

    "read name field from labelled Hlist" in {
      assert(userLaballedRecord('name) == "John")
    }

    "return list of keys from labelled Hlist" in {
      val keys = Keys[userGen.Repr]
      assert(keys() == 'name :: 'age :: HNil)
    }

    "return list of values from labelled Hlist" in {
      assert(userLaballedRecord.values == "John" :: 25 :: HNil)
    }
  }

  "Polymorphic function" should {
    "calculate length of variant input types" in {
      import shapeless._
      object polyLength extends Poly1 {
        implicit val listCase   = at[List[Int]](i => i.length)
        implicit val stringCase = at[String](d => d.length)
        implicit val arrayCase  = at[Array[Int]](d => d.length)
      }

      val list = List(1, 2) :: "123" :: Array(1, 2, 3, 4) :: HNil

      assert(polyLength(List(1, 2)) == 2)
      assert(polyLength("123") == 3)
      assert(polyLength(Array(1, 2, 3, 4)) == 4)
      assert(list.map(polyLength) == 2 :: 3 :: 4 :: HNil)
    }
  }
}
