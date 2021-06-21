import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should
import io.circe._
import io.circe.generic.semiauto._
import io.circe.parser._

class CustomDecoderSpec extends AnyFlatSpec with should.Matchers {

  val jsonStringWithNullArray =
    """{
      |  "textField" : "textContent",
      |  "nestedObject" : {
      |    "arrayField" : null
      |  }
      |}""".stripMargin

  val jsonStringWithMissingArray =
    """{
      |  "textField" : "textContent",
      |  "nestedObject" : {
      |  }
      |}""".stripMargin

  val jsonStringWithArray =
    """{
      |  "textField" : "textContent",
      |  "nestedObject" : {
      |    "arrayField" : [1, 2]
      |  }
      |}""".stripMargin

  val jsonStringWithEmptyArray =
    """{
      |  "textField" : "textContent",
      |  "nestedObject" : {
      |    "arrayField" : []
      |  }
      |}""".stripMargin

  case class Nested(arrayField: List[Int])

  case class OurJson(
                      textField: String,
                      numericField: Option[Int],
                      booleanField: Option[Boolean],
                      nestedObject: Nested
                    )

  implicit val decodeNested: Decoder[Nested] = (c: HCursor) => for {
    arrayField <- c.downField("arrayField").as[Option[List[Int]]]
  } yield {
    val flattenedArray = arrayField.getOrElse(Nil)
    Nested(flattenedArray)
  }

  implicit val jsonDecoder: Decoder[OurJson] = deriveDecoder[OurJson]

  "A custom decoder" should "decode a JSON with a null array value" in {
    decode[OurJson](jsonStringWithNullArray) shouldEqual Right(OurJson("textContent", None, None, Nested(Nil)))
  }

  it should "decode a JSON with a missing field" in {
    decode[OurJson](jsonStringWithMissingArray) shouldEqual Right(OurJson("textContent", None, None, Nested(Nil)))
  }

  it should "decode a JSON with an existing array value" in {
    decode[OurJson](jsonStringWithArray) shouldEqual Right(OurJson("textContent", None, None, Nested(List(1, 2))))
  }

  it should "decode a JSON with an empty array" in {
    decode[OurJson](jsonStringWithEmptyArray) shouldEqual Right(OurJson("textContent", None, None, Nested(Nil)))
  }
}
