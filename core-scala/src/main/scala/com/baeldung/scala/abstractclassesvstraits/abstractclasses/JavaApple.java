package com.baeldung.scala.abstractclassesvstraits.abstractclasses;

public class JavaApple extends GroceryProduct {
    public JavaApple(String sku) {
        super(sku);
    }
    @Override
    public double price() {
        return 100;
    }
}
