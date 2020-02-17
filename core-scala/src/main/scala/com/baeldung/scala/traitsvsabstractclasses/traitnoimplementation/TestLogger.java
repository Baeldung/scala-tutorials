package com.baeldung.scala.traitsvsabstractclasses.traitnoimplementation;

class LoggerImpl implements Logger {
  public void log(String s) { System.out.println(s); }
}
public class TestLogger {
  public static void main(String[] args) {
    new LoggerImpl().log("Here we log!");
  }
}
