package com.mm.strategy;

import com.sun.org.apache.regexp.internal.REUtil;

/**
 * 优惠操作实体
 * 这里定义了优惠的逻辑
 */
public class CashContext {
    private  CashSuper cashSuper ;
//    public CashContext(CashSuper cashSuper) {
//        this.cashSuper = cashSuper;
//    }
    public CashContext(CashType type){
        switch (type) {
            case normal:
                this.cashSuper = new CashNormal();
            case returnCash:
                this.cashSuper = new CashRebate(0.8);
            case rebate:
                this.cashSuper = new CashReturn(300, 20);
        }
    }

    /**
     * 传入本金，计算。使用什么策略。由传入的实体决定
     * @param principal
     * @return
     */
    public double getResult(double principal) {
        return cashSuper.acceptCash(principal);
        //这里还可以做其他操作
    }
}
