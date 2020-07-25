package com.baeldung.scala.abstractclass.vs.trait;


import com.baeldung.scala.abstractclass.vs.trait.abstractclass.Person;

public class Student extends Person {

    private String firstName, lastName;

    public Student(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String firstName() {
        return firstName;
    }

    @Override
    public String lastName() {
        return lastName;
    }
}

/*
If Student is implemented by implementing a Scala trait which contains an implementation,
you will get a compilation error
 */

//public class StudentFromTrait implements import com.baeldung.scala.abstractclass.vs.trait.trait.Person {
//    @Override
//    public String firstName() {
//        return null;
//    }
//
//    @Override
//    public String lastName() {
//        return null;
//    }
//
//    @Override
//    public String name() {
//        return super.name();  //THIS LINE WILL GIVE A COMPILATION ERROR
//    }
//}

