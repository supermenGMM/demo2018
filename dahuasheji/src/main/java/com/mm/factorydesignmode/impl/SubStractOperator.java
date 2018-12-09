package com.mm.factorydesignmode.impl;

import com.mm.factorydesignmode.Operator;

public class SubStractOperator implements Operator {
    @Override
    public double calculator(double a, double b) {
        return a-b;
    }
}
