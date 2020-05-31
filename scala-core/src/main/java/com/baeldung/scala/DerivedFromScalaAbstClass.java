package com.baeldung.scala;

import com.baeldung.scala.TraitAbstractClassComparison.*;

public class DerivedFromScalaAbstClass extends AbstClass {

    public DerivedFromScalaAbstClass(String exampleParam) {
        super(exampleParam);
    }

    @Override
    public void abstMethod() {
        System.out.println(" Abstract method implementation ");
    }
}
