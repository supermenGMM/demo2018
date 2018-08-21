package com.mm.lambdademo;

import sun.applet.Main;

public class LambdaDemo {
    interface MathOperation {
        int operation(int a,int b);
    }
    interface  GreetingService{
        void sayMessage(String message);
    }
    private int operation(int a,int b,MathOperation mathOperation){
       return  mathOperation.operation(a,b);
    }

    public static  void main(String[] args){

        MathOperation mathOperation = (int a, int b)->a+b;
        MathOperation substraction =(a,b)->{return a-b;};
        MathOperation multiplication =(int a,int b)->{return a*b;};

        LambdaDemo lambdaDemo = new LambdaDemo();
        lambdaDemo.operation(10, 12, mathOperation);
        lambdaDemo.operation(2, 4, substraction);
        lambdaDemo.operation(4, 4, multiplication);

        GreetingService greetingService = message ->{
            System.out.println("我的信息:"+message);
        };
        greetingService.sayMessage("hello");
    }

}
