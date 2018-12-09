package com.mm.decorate;

/**
 * 装饰类父类
 */
public abstract class DecorateCash  implements CashSuper {
    protected CashSuper cashSuper ;

    public DecorateCash(CashSuper cashSuper) {
        this.cashSuper = cashSuper;
    }
}
