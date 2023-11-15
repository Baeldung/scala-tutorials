import akka.actor.ActorSystem
import akka.stream.Materializer
import akka.stream.testkit.scaladsl.TestSink
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class ReadingCSVTest extends AnyFlatSpec with Matchers {
  implicit val system: ActorSystem = ActorSystem("ReadingCSVTest")
  implicit val materializer = Materializer.createMaterializer(system)

  "read" should "read csv correctly" in {
    ReadingCSV
      .read("src/test/resources/readingcsv.csv")
      .runWith(TestSink[String]())
      .request(5)
      .expectNext(
        """"Name","Age"""",
        """"Bob",24""",
        """"Jane",47""",
        """"Imran",32""",
        """"Trisha",50"""
      )
      .expectComplete()
  }

  "readFromString" should "read csv correctly" in {
    ReadingCSV
      .readFromString(""""Name","Age"
                                |"Bob",24
                                |"Jane",47
                                |"Imran",32
                                |"Trisha",50""".stripMargin)
      .runWith(TestSink[String]())
      .request(5)
      .expectNext(
        """"Name","Age"""",
        """"Bob",24""",
        """"Jane",47""",
        """"Imran",32""",
        """"Trisha",50"""
      )
      .expectComplete()
  }

  "readWithAlpakka" should "read csv correctly" in {
    ReadingCSV
      .readWithAlpakka("src/test/resources/readingcsv.csv")
      .runWith(TestSink[List[String]]())
      .request(5)
      .expectNext(
        List("Name", "Age"),
        List("Bob", "24"),
        List("Jane", "47"),
        List("Imran", "32"),
        List("Trisha", "50")
      )
      .expectComplete()
  }
  "readWithAlpakkaAsMap" should "read csv correctly" in {
    ReadingCSV
      .readWithAlpakkaAsMap("src/test/resources/readingcsv.csv")
      .runWith(TestSink[Map[String, String]]())
      .request(5)
      .expectNext(
        Map("Name" -> "Bob", "Age" -> "24"),
        Map("Name" -> "Jane", "Age" -> "47"),
        Map("Name" -> "Imran", "Age" -> "32"),
        Map("Name" -> "Trisha", "Age" -> "50")
      )
      .expectComplete()
  }
}
