package com.baeldung.scala.abstractclassesvstraits.traits;

class JavaApple implements GroceryProduct {
    @Override
    public String sku() {
        return null;
    }

    @Override
    public double price() {
        return 0;
    }

    @Override
    public double tax() {
        return 0;
    }

    @Override
    public double calculateTaxFee() {
        return GroceryProduct.super.calculateTaxFee();
    }
}
