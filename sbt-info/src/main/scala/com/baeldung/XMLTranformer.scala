package com.baeldung

trait XMLTranformer {
  def toXML(input: Map[String, Any]): String = {
    s"""
      <buildInfo>
        <name>${input.get("name")}</name>
        <version>${input.get("version")}</version>
        <scalaVersion>${input.get("scalaVersion")}</scalaVersion>
      </buildInfo>
    """
  }
}
