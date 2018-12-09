package com.mm.factorydesignmode;



import java.io.IOException;
import java.util.Scanner;

public class Calculator {

    public static void main(String[] args) throws IOException {
        try {
            Scanner scanner = new Scanner(System.in);
            String[] arr = new String[3];

            int n= 0;
          while(n<3){
              System.out.println("请输入第"+n+"个数字:");
              arr[n++] = scanner.nextLine();
          }


            Operator operator = OperatorFactory.createOperator(arr[1]);
            if (operator == null) {
                System.out.println("您输入的操作符z暂不支持");
                return;
            }
            double calculator = operator.calculator(Double.valueOf(arr[0]), Double.valueOf(arr[2]));
            System.out.println("计算结果为："+calculator);
        } catch (Exception e) {

            e.printStackTrace();
            System.out.println("请输入正确的操作符");
        }


    }


}
