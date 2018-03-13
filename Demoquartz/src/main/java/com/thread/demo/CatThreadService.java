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
		for (int i = 0; i < 10; i++) {
			Cat cat = completionService.take().get();
			System.out.println(cat);
		}
		executorService.shutdown();
	}
}
