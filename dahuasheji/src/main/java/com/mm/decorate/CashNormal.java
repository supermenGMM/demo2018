package com.mm.decorate;

/**作为被装饰的类.
 */
public class CashNormal implements CashSuper {


    @Override
    public double acceptCash(double principal) {
        return principal;
    }
}
