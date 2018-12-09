package com.mm.strategy;

public class CashRebate implements CashSuper {

    private double rate ;

    public CashRebate(double rate) {
        this.rate = rate;
    }
    @Override
    public double acceptCash(double principal) {
        return principal*rate;
    }
}
