package com.mm.factorydesignmode;

import com.mm.factorydesignmode.impl.AddOperator;
import com.mm.factorydesignmode.impl.DivideOperator;
import com.mm.factorydesignmode.impl.MultiplicationOperator;
import com.mm.factorydesignmode.impl.SubStractOperator;

public class OperatorFactory {
    public static Operator createOperator(String symbol) {
        switch (symbol) {
            case "+":
                return new AddOperator();
            case "-":
                return new SubStractOperator();
            case "*":
                return new MultiplicationOperator();
            case "/":
                return new DivideOperator();
        }
        return null;
    }
}
