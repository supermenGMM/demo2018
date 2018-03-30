package com.effective.java.singleton;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * 饿汉式单例模式
 * @author supermenG
 *
 */
public class Person implements Serializable {
	private static Person INSTENCE  = new Person();
	private Person(){}
	public static Person getInstence(){
		return INSTENCE;
	}
	/**
	 * 不添加该方法，反序列话时，出现多实例的情况
	 * 该方法方法名和返回值固定
	 * @return
	 */
	public Object readResolve(){
		return INSTENCE;
	}
	public static void main(String[] args) throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
//		利用反射创建对象
		Person instence1 = Person.getInstence();
		Class<Person> personClass = Person.class;
		
		Constructor<Person> constructor = personClass.getDeclaredConstructor(new Class[]{});
		constructor.setAccessible(true);
		Person instence2 = constructor.newInstance(new Object[]{});
		System.out.println(instence1==instence2);
	}
}
