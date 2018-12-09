package com.mm.decorate;

public enum CashType {
    normal("正常"),
    returnCash("满减"),
    rebate("打折");

    private String desc;

    CashType(String desc) {
        this.desc = desc;
    }
}
