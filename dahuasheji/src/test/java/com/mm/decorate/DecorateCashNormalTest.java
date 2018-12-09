package com.mm.decorate;


import org.junit.Test;

import java.security.Principal;

/**
 * 测试装饰者模式
 */
public class DecorateCashNormalTest {
    @Test
    public void test() {
        double pricinpal = 500;
        CashNormal cashNormal = new CashNormal();
        CashRebate cashRebate = new CashRebate(cashNormal, 0.9);
        CashReturn cashReturn = new CashReturn(cashRebate, 400, 50);
        double v = cashReturn.acceptCash(pricinpal);
        System.out.println(v);
    }

}
