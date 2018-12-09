package com.mm.decorate;

import java.io.BufferedReader;

/**
 * 满减
 */
public class CashReturn extends DecorateCash {
    private double cell;
    private double reduced;

    public CashReturn(CashSuper cashSuper,double cell, double reduced) {
        super(cashSuper);
        this.cell = cell;
        this.reduced = reduced;
    }
    @Override
    public double acceptCash(double principal) {
        double newprincipal = cashSuper.acceptCash(principal);
        int num = (int) (newprincipal/cell);
        return newprincipal - num*reduced;
    }
}
