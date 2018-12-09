package com.mm.strategy;

public class CashNormal implements CashSuper {
    @Override
    public double acceptCash(double principal) {
        return principal;
    }
}
