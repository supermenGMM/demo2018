package com.mm.defaultfunction;

import com.sun.xml.internal.ws.api.streaming.XMLStreamReaderFactory;

import javax.sound.midi.Soundbank;

/**
 * 默认方法
 */
public class Demo implements Animal,Cat {
    public void say(){
        Cat.super.say();
    }
}
interface Animal{
    default void say(){
        System.out.println("我是默认方法");
    }
    static void hello(){
        System.out.println("我是静态默认方法");
    }
}
interface  Cat{
    default void say(){
        System.out.println("miaomiao");
    }
    static void hello(){
        System.out.println("猫猫静态");
    }
}


