package com.thread.demo;

import java.util.concurrent.Callable;

public class CatThread implements Callable<Cat>{

	public Cat call() throws Exception {
		System.out.println("call  execute");
		return new CatBuilder().age(1).name("string").build();
	}
	
}
