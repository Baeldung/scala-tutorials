package com.baeldung.scala.javaabstractclass;


import com.baeldung.scala.abstractclasstrait.demo.Movies;

public class ScalaDemo extends Movies {

    @Override
    public void addMovie() {
        System.out.println("Add Movie");
    }
}
