package com.mm.lambdademo;

import java.util.function.Consumer;

public class LambdaDemo2 {
    interface GreetingInterface{
        void sayMessage(String message);
    }
    interface Converter{
        void convert(int i);
    }

    public static void main(String[] args){
        int a = 2;
        Converter converter= (i) -> {
            //int a= 3;//不能定义和所属方法中局部变量同名的变量名
        //    System.out.println(i+a);//报错是因为，在匿名内部类中，引用局部变量必须是final类型。
        };
        converter.convert(3);;
        a=3;
        //其实lambda表达式就是创建匿名内部类对象的简写



    }
}
