package com.mm.method.references;

import javax.sound.midi.Soundbank;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 方法引用
 * @param <T>
 */
@FunctionalInterface
interface Supplier<T>{
    T get();
}

public class Car {
    private int num;
    private String color;

    public  static  Car create(Supplier<Car> supplier){
        return supplier.get();
    }

    public Car() {

    }

    public static void run(Car car) {
        System.out.println("小汽车");
    }

    public void fix(Car car) {
        this.num=car.num;
    }

    public String getColor() {
        return color;
    }

    public void say() {
        System.out.println("zzzz");
    }

    public static void main(String[] args) {
        Car car = Car.create(Car::new);//构造器引用
        List<Car> list  = Arrays.asList(car);
        list.forEach(Car::say);//静态方法引用                 参数是car
        list.forEach(Car::run);//特定类的任意对象的方法引用   无参数
        list.forEach(car::fix);//特定对象的方法引用           参数必须是car


        List<String> list2 = new ArrayList<>();
        list2.add("佩奇");
        list2.add("肖恩");
        list2.forEach(System.out::println);//参数就是forEach的对象
    }

}
