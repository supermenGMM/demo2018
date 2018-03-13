package com.mm.test.thread.callable;

import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Service {
	
	public Cat executeTread(List<Cat> cats) throws InterruptedException, ExecutionException {
		ExecutorService executorService = Executors.newFixedThreadPool(3);
		CompletionService<Cat> completionService = new ExecutorCompletionService<Cat>(executorService);
		for (Cat cat : cats) {
			completionService.submit(new MyCallable(cat));
		}
		Cat cat=null;
		for (int i = 0; i < cats.size(); i++) {
			cat = completionService.take().get();
			System.out.println(cat+"----get");
			if(cat!=null){
				System.out.println("shutdown");
				executorService.shutdown();
				break;
			}
		}
		return cat;
	}
	public  class MyCallable implements Callable<Cat> 
	{
		private Cat cat;
		public MyCallable(Cat cat){
			this.cat = cat;
		}
		public Cat call() throws Exception {
			cat.setAge(cat.getAge()+22);
			cat.setName("姓名:"+cat.getName());
			System.out.println("睡觉------"+Thread.currentThread());
			Thread.sleep(new Random().nextInt(10000));
			System.out.println("执行了call");
			return cat;
		}
	}

}
