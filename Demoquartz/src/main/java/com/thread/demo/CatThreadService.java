package com.thread.demo;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CatThreadService<V> {
	public void execute() throws InterruptedException, ExecutionException{
		ExecutorService executorService = Executors.newFixedThreadPool(10);
		ExecutorCompletionService<Cat> completionService = new ExecutorCompletionService<Cat>(executorService);
		Future<Cat> submit = completionService.submit(new CatThread());
		Cat cat = completionService.take().get();
		/*for (int i = 0; i < 10; i++) {
			
			System.out.println(cat+",i:"+i);
			System.out.println(Thread.currentThread());
		}*/
		executorService.shutdown();
	}
}
