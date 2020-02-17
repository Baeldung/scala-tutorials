package com.baeldung.scala.traitsvsabstractclasses.traitwithimplementation;

class LoggerImpl extends LoggerWrapper {}

public class TestLogger {
  public static void main(String[] args) {
    new LoggerImpl().log("Here we log");
  }
}
