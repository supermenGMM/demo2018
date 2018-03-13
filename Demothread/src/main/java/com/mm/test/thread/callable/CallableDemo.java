package com.mm.test.thread.callable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableDemo {
	public static List<Cat> cats;
	static{
		cats = new ArrayList<Cat>();
		cats.add(new CatBuilder().age(1).name("赵孟").build());
		cats.add(new CatBuilder().age(2).name("肯德基").build());
	}
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		Cat cat = new Service().executeTread(cats);
		System.out.println(cat);
	}
}
