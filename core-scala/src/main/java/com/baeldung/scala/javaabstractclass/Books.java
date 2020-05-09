package com.baeldung.scala.javaabstractclass;

import java.util.ArrayList;

public abstract class Books {
    public ArrayList<String> bookList= new ArrayList<String>();
    public Books() {
        bookList.add("Bible");
        bookList.add("Novel");
        bookList.add("Technology");
    }

    public String getFirstBook(){
            return bookList.get(0);
        }
    abstract String getRecommendation();
}
