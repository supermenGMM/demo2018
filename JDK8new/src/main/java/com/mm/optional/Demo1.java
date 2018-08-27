package com.mm.optional;

import com.mm.stream.Demo;

import javax.swing.text.html.Option;
import java.util.Optional;

public class Demo1 {
    public static void main(String[] args){
        Integer integer1= null;
        Integer integer2 = new Integer(10);
        //Optional.ofNullable -->允许传递null参数
        Optional<Integer> optional1 = Optional.ofNullable(integer1);
        //不能为null
        Optional<Integer> optional2 = Optional.of(integer2);

        Integer sum = new Demo1().sum(optional1, optional2);
        System.out.println(sum+"-----和");
    }

    public Integer sum(Optional<Integer> a,Optional<Integer> b){
        //Optional.isPresent 判断值是否存在
        System.out.println("第一个参数值存在：" + a.isPresent());
        System.out.println("第二个参数值存在："+b.isPresent());

        Integer i1 = a.orElse(new Integer(0));
        Integer i2 = b.get();
        return i1+i2;

    }

}
