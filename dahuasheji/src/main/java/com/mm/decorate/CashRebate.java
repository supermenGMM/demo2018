package com.mm.decorate;

/**
 * 装饰类1
 */
public class CashRebate extends DecorateCash {

    private double rate ;

    public CashRebate(CashSuper cashSuper,double rate) {
        super(cashSuper);
        this.rate = rate;
    }
    @Override
    public double acceptCash(double principal) {
        double newprincipal = cashSuper.acceptCash(principal);
        return newprincipal*rate;
    }
}
