import sbt._

// Define a custom object for Java specification version compatibility
object CompatibleJavaVersion extends VersionNumberCompatibility {
  def name = "Java specification compatibility"

  def isCompatible(current: VersionNumber, required: VersionNumber): Boolean =
    current.numbers
      .zip(required.numbers)
      .foldRight(required.numbers.size <= current.numbers.size) {
        case ((curr, req), acc) => (curr > req) || (curr == req && acc)
      }

  def apply(current: VersionNumber, required: VersionNumber): Boolean =
    isCompatible(current, required)
}
