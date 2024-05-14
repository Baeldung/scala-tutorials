/** This is an internal scala-cli script written just to reduce manual work of
  * renaming files and class names to follow naming standards. This doesn't fully correct all the class names,
  * however reduces the manual effort by 90%. 
  */
//> using toolkit default
import os._

object RenameClassNames {
  def main(args: Array[String]): Unit = {
    val directory = os.pwd / os.up

    os.walk(directory)
      .filter(_.ext == "scala")
      .filter(_.toString.contains("src/test/scala"))
      .filterNot(_.toString.endsWith("UnitTest.scala"))
      .filter(f =>
        f.toString.endsWith("Test.scala") || f.toString.endsWith("Spec.scala") || f.toString.endsWith("Tests.scala") || f.toString.endsWith("Suite.scala")
      )
      .foreach { _filePath =>
        val _fileName = _filePath.last

        val newFileName = _filePath.toString
          .replace("Test.scala", "UnitTest.scala")
          .replace("Spec.scala", "UnitTest.scala")
          .replace("Suite.scala", "UnitTest.scala")
          .replace("Tests.scala", "UnitTest.scala")

        // rename file
        val newFilePath = os.Path(newFileName)
        os.move(_filePath, newFilePath)

        val content = os.read(newFilePath)
        val existingClassName = getClassFromContent(content)

        val fileNameWithoutExtension = newFilePath.last.dropRight(6)

        def isTestClass(existingClassName: String): Boolean = {
          existingClassName
            .endsWith("Spec") || existingClassName.endsWith("Test")
        }

        // Rename the class if it doesn't match the filename
        if (
          isTestClass(
            existingClassName
          ) && existingClassName.toLowerCase != fileNameWithoutExtension.toLowerCase
        ) {
          // Update the content with the new class name
          val updatedContent = content.replaceAll(
            s"class\\s+$existingClassName",
            s"class $fileNameWithoutExtension"
          )

          // Now, rename the class
          os.write.over(newFilePath, updatedContent)
          println(
            s"Renamed class in ${newFilePath.last} to $fileNameWithoutExtension"
          )
        }
      }
  }

  // Extract class name from file content
  def getClassFromContent(content: String): String = {
    val classNameRegex = """class\s+(\w+)""".r
    val matchResult = classNameRegex.findFirstMatchIn(content)
    matchResult match {
      case Some(m) => m.group(1)
      case None    => ""
    }
  }
}
