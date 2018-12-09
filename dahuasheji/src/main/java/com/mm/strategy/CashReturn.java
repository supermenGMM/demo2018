package com.mm.strategy;

/**
 * 满减
 */
public class CashReturn implements CashSuper{
    private double cell;
    private double reduced;

    public CashReturn(double cell, double reduced) {
        this.cell = cell;
        this.reduced = reduced;
    }
    @Override
    public double acceptCash(double principal) {
        int num = (int) (principal/cell);
        return principal - num*reduced;
    }
}
