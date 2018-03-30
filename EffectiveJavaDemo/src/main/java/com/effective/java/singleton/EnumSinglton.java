package com.effective.java.singleton;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public enum EnumSinglton {
	INSTENCE;
	private Person person;

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}
	
	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
		File file = new File("instence");
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
		EnumSinglton instence1 = EnumSinglton.INSTENCE;
		Person person1 = instence1.getPerson();
		instence1.setPerson(Person.getInstence());
		Person person0 = instence1.getPerson();
		System.out.println(person0==person1);
		oos.writeObject(instence1);
		oos.close();
		
		EnumSinglton enumSinglton = (EnumSinglton) ois.readObject();
		ois.close();
		System.out.println(enumSinglton==instence1);
		Person person2 = enumSinglton.getPerson();
		System.out.println(person1==person2);
		
	}
	
}
