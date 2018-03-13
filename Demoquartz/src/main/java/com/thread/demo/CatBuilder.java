package com.thread.demo;


public class CatBuilder {
	private Cat cat;
	public CatBuilder (){
		cat = new Cat();
	}
	public Cat build(){
		return cat;
	}
	public CatBuilder age(int age){
		cat.setAge(age);
		return this;
	}
	public CatBuilder name(String name){
		cat.setName(name);
		return this;
	}
}
