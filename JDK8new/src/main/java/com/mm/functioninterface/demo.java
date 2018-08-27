package com.mm.functioninterface;

import sun.rmi.log.LogInputStream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * 函数式接口
 *
 * 其实就是提供一些接口，对于不同的入参和返回值，提供了不同 的接口
 */
public class demo {
    public static void main(String[] args){
        List<Integer> ints = Arrays.asList(1, 3, 4, 45, 5, 6, 7);
        System.out.println("输出所有数据:");
        eval(ints, n -> true);

        //判断是否是偶数
        System.out.println("输出所有偶数");
        eval(ints,n->n%2==0);

    }

    private static void eval(List<Integer> list, Predicate<Integer> predicate) {
        for (Integer i :
                list) {
           if( predicate.test(i))
               System.out.print(i+"  ");
        }
        System.out.println();
    }


}
