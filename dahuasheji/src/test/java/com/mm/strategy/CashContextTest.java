package com.mm.strategy;

import org.junit.Test;

public class CashContextTest {

    @Test
    public void test(){
        CashContext cashContext = new CashContext(CashType.returnCash);
        double result = cashContext.getResult(450);
        System.out.println("打折后的价格是："+result);
    }

}
