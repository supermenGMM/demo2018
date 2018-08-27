package com.mm.stream;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Demo {
    public static void main(String[] args){
        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
        //创建流--
        //串行流
        List<String> collect = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());
        for (String s :
                collect) {
            System.out.print(s+",");
        }
        System.out.println();
        //并行流
        strings.parallelStream().filter(string->!string.isEmpty()).forEach(System.out::print);
        System.out.println();

        //foreach迭代
        //limit 获取指定数量的流
        Random random = new Random();
        random.ints().limit(10).forEach(System.out::print);
        System.out.println();

        //map 映射每个元素到对应结果
//        distinct去重
        List<Integer> integers = Arrays.asList(3, 2, 4, 2, 4, 5);
        List<Integer> collect2 = integers.stream().map(i -> i * i).distinct().collect(Collectors.toList());
        for (Integer i:
             collect2) {
            System.out.print(i+",");
        }
        System.out.println();

        //sorted 对流进行排序
        Random random2 = new Random();
        random2.ints(10,1,10).sorted().forEach(System.out::println);

        //并行parallel程序
        List<String> strs = Arrays.asList("abc","","v","lemon","strawbarry","pill");
        long count = strs.parallelStream().filter(string -> string.isEmpty()).count();
        System.out.println("count="+count);

        //Collectors将流转换为集合元素

        //统计结果的收集器
        List<Integer > numbers =Arrays.asList(2,4,5,6,7,9,7,8);
        IntSummaryStatistics statistic = numbers.stream().mapToInt(x -> x).summaryStatistics();
        System.out.println("集合中最大数:" + statistic.getMax());
        System.out.println("集合中最小数：" + statistic.getMin());
        System.out.println("所有数之和：" + statistic.getSum());
        System.out.println("平均数"+statistic.getAverage());
    }
}
