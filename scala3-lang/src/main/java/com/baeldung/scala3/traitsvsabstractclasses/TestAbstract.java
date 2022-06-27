package com.baeldung.scala3.traitsvsabstractclasses;

public class TestAbstract extends SomeAbstractClass {
    @Override
    public void doSomething() {
        System.out.println("something");
    }

    public static void main(String[] args) {
        TestAbstract example = new TestAbstract();
        example.doSomething();
    }
}
