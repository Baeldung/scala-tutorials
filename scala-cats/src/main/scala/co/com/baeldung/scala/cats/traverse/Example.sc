import cats.implicits._

val list1 = List(Some(1), Some(2), None)
// List[Option[Int]] = List(Some(1), Some(2), None)

val traversedNone = list1.traverse(identity)
// Option[List[Int]] = None

val list2: List[Option[Int]] = List(Some(1), Some(2), Some(3))
// List[Option[Int]] = List(Some(1), Some(2), Some(3))

val traversedSome = list2.traverse(identity)
// Option[List[Int]] = Some(List(1, 2, 3))