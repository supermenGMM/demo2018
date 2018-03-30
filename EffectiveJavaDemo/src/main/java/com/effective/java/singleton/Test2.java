package com.effective.java.singleton;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class Test2 {
	public static void main(String[] args) {
//		序列化导致不是单例
		File file  = new File("singleton");
		ObjectOutputStream oos = null;
		ObjectInputStream ois = null;
		Person person1 = Person.getInstence();
		try {
			oos = new ObjectOutputStream(new FileOutputStream(file));
			oos.writeObject(person1);
			oos.close();
			
			ois = new ObjectInputStream(new FileInputStream(file));
			Person person2 = (Person) ois.readObject();
			System.out.println(person1==person2);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
