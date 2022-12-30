package com.baeldung;
import com.baeldung.scala3.targetname.Calculator;

public class MyJavaCalculator {
    public static void main(String[] args) {
        System.out.println("This is from java!");
        Calculator calc = new Calculator();
        Integer sum = calc.plus(10,20);
        System.out.println("Sum = "+sum);
        String combined = calc.plus("asd", "xyz");
        System.out.println(combined);
        
        calc.weirdType();
        
        System.out.println(calc.calcType());

        calc.InnerCalculator().print();

    }
}