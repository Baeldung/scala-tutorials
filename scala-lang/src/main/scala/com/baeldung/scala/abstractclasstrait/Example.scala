abstract class MyAbstractClass(custom: String) {

  def implementedMethod(): String = {
    s"MyAbstractClass::implementedMethod::$custom"
  }

  def abstractMethod: String

}

class MySubClass(custom: String) extends MyAbstractClass(custom) {

  override def abstractMethod: String = {
    s"MySubClass::abstractMethod::$custom"
  }
}

trait JsonSerializer {

  def serializeJson(): String = {
    "{JSON}"
  }

}

trait XmlSerializer {
  def serializeXML(): String = {
    "<XML></XML>"
  }
}

class ClassWithTraits extends JsonSerializer with XmlSerializer {

  def printAll: String = {
    s"Json:${serializeJson()} // XML:${serializeXML()}"
  }

}
