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
            System.out.println(i+a);
        };
        converter.convert(3);;

    }
}
