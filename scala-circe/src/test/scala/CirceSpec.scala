import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should

class CirceSpec extends AnyFlatSpec with should.Matchers {

  val jsonString =
    """{
      |  "textField" : "textContent",
      |  "numericField" : 123,
      |  "booleanField" : true,
      |  "nestedObject" : {
      |    "arrayField" : [
      |      1,
      |      2,
      |      3
      |    ]
      |  }
      |}""".stripMargin

  val jsonStringWithMissingFields =
    """{
      |  "textField" : "textContent",
      |  "nestedObject" : {
      |    "arrayField" : null
      |  }
      |}""".stripMargin

  "Circe" should "validate a valid JSON string" in {
    import io.circe._, io.circe.parser._

    val parseResult: Either[ParsingFailure, Json] = parse(jsonString)

    parseResult match {
      case Left(parsingError) => throw new IllegalArgumentException(s"Invalid JSON object: ${parsingError.message}")
      case Right(json) =>
        val numbers = json \\ "numericField"
        val firstNumber: Option[Option[JsonNumber]] = numbers.collectFirst{
          case field => field.asNumber
        }

        val singleOption: Option[Int] = firstNumber.flatten.flatMap(_.toInt)

        singleOption shouldEqual  Some[Int](123)
    }
  }

  it should "convert JSON into object using converters and back to original string" in {
    import io.circe._, io.circe.generic.semiauto._, io.circe.parser._, io.circe.syntax._

    case class Nested(arrayField: List[Int])

    case class OurJson(
                        textField: String,
                        numericField: Int,
                        booleanField: Boolean,
                        nestedObject: Nested
                      )

    implicit val nestedDecoder: Decoder[Nested] = deriveDecoder[Nested]
    implicit val jsonDecoder: Decoder[OurJson] = deriveDecoder[OurJson]

    decode[OurJson](jsonString) shouldEqual Right(OurJson("textContent", 123, true, Nested(List(1, 2, 3))))

    val decoded = decode[OurJson](jsonString)

    implicit val nestedEncoder: Encoder[Nested] = deriveEncoder[Nested]
    implicit val jsonEncoder: Encoder[OurJson] = deriveEncoder[OurJson]

    decoded match {
      case Right(decodedJson) =>
        val jsonObject: Json = decodedJson.asJson
        jsonObject.spaces2 shouldEqual jsonString
    }
  }

  it should "decode a JSON with missing fields" in {
    import io.circe._, io.circe.generic.semiauto._, io.circe.parser._

    val jsonStringWithMissingFields =
      """{
        |  "textField" : "textContent",
        |  "nestedObject" : {
        |    "arrayField" : null
        |  }
        |}""".stripMargin

    case class Nested(arrayField: Option[List[Int]])

    case class OurJson(
                        textField: String,
                        numericField: Option[Int],
                        booleanField: Option[Boolean],
                        nestedObject: Nested
                      )

    implicit val nestedDecoder: Decoder[Nested] = deriveDecoder[Nested]
    implicit val jsonDecoder: Decoder[OurJson] = deriveDecoder[OurJson]

    decode[OurJson](jsonStringWithMissingFields) shouldEqual Right(OurJson("textContent", None, None, Nested(None)))
  }

  it should "decode a JSON with missing fields using a custom decoder" in {
    import io.circe._, io.circe.generic.semiauto._, io.circe.parser._

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

    decode[OurJson](jsonStringWithMissingFields) shouldEqual Right(OurJson("textContent", None, None, Nested(Nil)))

    val jsonStringWithArray =
      """{
        |  "textField" : "textContent",
        |  "nestedObject" : {
        |    "arrayField" : [1, 2]
        |  }
        |}""".stripMargin

    decode[OurJson](jsonStringWithArray) shouldEqual Right(OurJson("textContent", None, None, Nested(List(1, 2))))
  }

  it should "use automatic decoder derivation" in {
    import io.circe.generic.auto._, io.circe.parser

    case class Nested(arrayField: List[Int])

    case class OurJson(
                        textField: String,
                        numericField: Int,
                        booleanField: Boolean,
                        nestedObject: Nested
                      )

    parser.decode[OurJson](jsonString) shouldEqual Right(OurJson("textContent", 123, true, Nested(List(1, 2, 3))))
  }
}
