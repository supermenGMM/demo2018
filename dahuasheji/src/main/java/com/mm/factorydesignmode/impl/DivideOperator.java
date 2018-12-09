package com.mm.factorydesignmode.impl;

import com.mm.factorydesignmode.Operator;

public class DivideOperator implements Operator {
    @Override
    public double calculator(double a, double b) {
        if(b==0){
            System.out.println("不能除0");
            return 0;
        }
        return a/b;
    }
}
